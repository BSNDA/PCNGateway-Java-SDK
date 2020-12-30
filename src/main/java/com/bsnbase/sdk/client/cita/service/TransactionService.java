package com.bsnbase.sdk.client.cita.service;


import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.cita.ReqKeyUpload;
import com.bsnbase.sdk.entity.req.cita.ReqKeyUploadBody;
import com.bsnbase.sdk.entity.res.cita.ResGetBlockHeight;
import com.bsnbase.sdk.entity.res.cita.ResKeyEscrow;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.common.HttpService;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import com.bsnbase.sdk.util.sm2.Sm2SignUtil;
import com.citahub.cita.abi.FunctionEncoder;
import com.citahub.cita.abi.datatypes.DynamicBytes;
import com.citahub.cita.abi.datatypes.Function;
import com.citahub.cita.abi.datatypes.Type;
import com.citahub.cita.abi.datatypes.generated.Bytes32;
import com.citahub.cita.abi.datatypes.generated.Uint256;
import com.citahub.cita.protocol.core.methods.request.Transaction;
import org.bouncycastle.crypto.CryptoException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class TransactionService {

    // nonce: generated randomly or depend on specific logic to avoid replay attack.
    private static Random nonce;

    // valid_until_block: timeout mechanism which should be set in (currentHeight, currentHeight + 100].
    // Transaction will be discarded beyond `valid_until_block`.
    private static Long valid_until_block = 80L;

    // quota: transaction execution fee for operation, like gasPrice * gasLimit in Ethereum.
    private static Long quota = 1000000L;

    /**
     * 密钥托管模式交易处理
     *
     * @param kes
     * @return
     * @throws IOException
     */
    public static ResKeyEscrow reqChainCode(@NotNull ReqKeyEscrow kes) throws IOException {
        String api = Config.config.getApi() + "/api/cita/v1/node/reqChainCode";
        BaseReqModel<ReqKeyEscrow> req = new BaseReqModel<ReqKeyEscrow>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(kes);

        HttpService<ReqKeyEscrow, ResKeyEscrow> httpService = new HttpService<ReqKeyEscrow, ResKeyEscrow>();
        BaseResModel<ResKeyEscrow> res = httpService.post(req, api, ResKeyEscrow.class);
        return res.getBody();
    }


    /**
     * 密钥上传模式交易处理
     *
     * @throws IOException
     */
    public static ResKeyEscrow nodeTrans(@NotNull ReqKeyUpload req) throws Exception {

        // 1 秘钥托管用户
        if (Config.config.getAppInfo().getCaType() == 1) {
            throw new GlobalException(ResultInfoEnum.FUNCTION_CALL_ERROR);
        }

        String api = Config.config.getApi() + "/api/cita/v1/node/trans";
        nonce = new Random(System.currentTimeMillis());

        // 获取当前块高
        ResGetBlockHeight resGetBlockHeight = NodeService.getBlockHeight();
        long blockIndex = 0;
        if (resGetBlockHeight != null && !resGetBlockHeight.getData().isEmpty()) {
            blockIndex = Long.parseLong(resGetBlockHeight.getData()) + valid_until_block;
        } else {
            throw new GlobalException(ResultInfoEnum.BLOCK_HEIGHT_ERROR);
        }

        // 获取FuncData
        String addFuncData = getAddFuncData(req);

        // 获取签名后的交易
        String rawTx = getRawTx(req, blockIndex, addFuncData);

        // 发送交易
        ReqKeyUploadBody transBody = new ReqKeyUploadBody();
        transBody.setContractName(req.getContractName());
        transBody.setTransData(rawTx);

        BaseReqModel<ReqKeyUploadBody> baseReqModel = new BaseReqModel<>();
        baseReqModel.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        baseReqModel.setBody(transBody);

        HttpService<ReqKeyUploadBody, ResKeyEscrow> httpService = new HttpService<>();
        BaseResModel<ResKeyEscrow> baseRes = httpService.post(baseReqModel, api, ResKeyEscrow.class);
        return baseRes.getBody();
    }

    /**
     * 获取交易签名字符串
     *
     * @param req
     * @param blockIndex
     * @param addFuncData
     * @return
     * @throws CryptoException
     * @throws IOException
     */
    private static String getRawTx(@NotNull ReqKeyUpload req, long blockIndex, String addFuncData) throws CryptoException, IOException {
        // Tx
        Transaction tx = Transaction.createFunctionCallTransaction(
                req.getContractAddr(),
                String.valueOf(Math.abs(nonce.nextLong())),
                quota,
                blockIndex,
                Config.config.getAppInfo().getVersion(),
                new BigInteger(Config.config.getAppInfo().getChannelId(), 16),
                "0",
                addFuncData);


        // Sign the transaction with sender's private key
        PrivateKey privateKey = Sm2SignUtil.getPrivateKey(Config.config.getPrk().getBytes());
        ECPrivateKey ecPrivateKey = (ECPrivateKey) privateKey;
        String pri = String.format("0x%s", ecPrivateKey.getS().toString(16));
        return tx.sign(pri, Transaction.CryptoTx.SM2, false);
    }

    /**
     * 获取FuncData
     *
     * @param req
     * @return
     */
    @NotNull
    private static String getAddFuncData(@NotNull ReqKeyUpload req) {
        // params
        List<Type> inputs = new ArrayList<>();

        switch (req.getFuncName()) {
            case "insert":
            case "update":
                inputs.add(new Bytes32(Common.getByte32(req.getArgs()[0])));
                inputs.add(new DynamicBytes(req.getArgs()[1].getBytes()));
                break;
            case "retrieve":
            case "remove":
                inputs.add(new Bytes32(Common.getByte32(req.getArgs()[0])));
                break;
            case "keyAtIndex":
                inputs.add(new Uint256(Long.parseLong(req.getArgs()[0])));
                break;
            default:
        }
        if (inputs.size() == 0) {
            throw new GlobalException(ResultInfoEnum.FUNCTION_ERROR);
        }

        // function
        Function addFunc = new Function(req.getFuncName(), inputs, Collections.emptyList());
        return FunctionEncoder.encode(addFunc);
    }
}
