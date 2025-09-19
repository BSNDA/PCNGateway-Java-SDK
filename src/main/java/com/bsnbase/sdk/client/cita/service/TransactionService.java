package com.bsnbase.sdk.client.cita.service;


import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.cita.ReqKeyUpload;
import com.bsnbase.sdk.entity.req.cita.ReqKeyUploadBody;
import com.bsnbase.sdk.entity.resp.cita.ResGetBlockHeight;
import com.bsnbase.sdk.entity.resp.cita.ResKeyEscrow;
import com.bsnbase.sdk.util.path.PathUtil;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.common.HttpService;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import com.bsnbase.sdk.util.sign.Sm2SignUtil;
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
import java.security.SecureRandom;
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
     * Invoke the smart contract in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * Example of default contract method call
     * Save data/Update data.
     * FuncName: insert/update
     * FuncParam: The first element is a byte32 string (key), the second element is a byte16 string (value), which can be obtained by Common.getByte32() and Common.getByte16().
     * Delete data/Get data:.
     * FuncName: remove/retrieve
     * FuncParam: the first element is a byte32 string (key)
     */
    public static ResKeyEscrow reqChainCode(@NotNull ReqKeyEscrow kes) throws IOException {
        String api = Config.config.getApi() + PathUtil.CITA_NODE_REQ_CHAIN_CODE;
        BaseReqModel<ReqKeyEscrow> req = new BaseReqModel<ReqKeyEscrow>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(kes);

        HttpService<ReqKeyEscrow, ResKeyEscrow> httpService = new HttpService<ReqKeyEscrow, ResKeyEscrow>();
        BaseResModel<ResKeyEscrow> res = httpService.post(req, api, ResKeyEscrow.class);
        return res.getBody();
    }


    /**
     * Invoke the smart contract in Public Key Upload Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * In the transaction of public key upload mode, the private key of the on-chain transaction is generated and saved by the user, and then the client will assemble and sign the on-chain data locally, and upload the signed data to the node gateway.
     * The gateway forwards the data to the corresponding blockchain node to initiate the transaction request. In this mode, the ABI of the contract and the contract address are required to assemble the data.
     * The ABI of the contract is obtained by compiling the contract when developing the contract, and the contract address can be obtained from the details page of the participated service.
     * The method to assemble the on-chain data has been implemented in the SDK of the gateway, and it can be called directly.
     * -----------------------------------------------------------------------------------------------------------------
     * Example of default contract method parameters
     * FuncParam: the parameter type of the preset chaincode package is already handled in Service, just pass String.
     * <p>
     * Save data
     * FuncName:insert
     * FuncParam:{"insert1219", "123456"}
     * <p>
     * Remove data
     * FuncName:remove
     * FuncParam:{"insert1219"}
     * <p>
     * Update data
     * FuncName:update
     * FuncParam:{"insert1219", "1234567"}
     * Retrieve data
     * FuncName:retrieve
     * FuncParam:{"insert1219"}
     * <p>
     * Get key by index
     * FuncName:keyAtIndex
     * FuncParam:{"1"}
     * -----------------------------------------------------------------------------------------------------------------
     */
    public static ResKeyEscrow nodeTrans(@NotNull ReqKeyUpload req) throws Exception {

        // 1 The user in Key Trust Mode
        if (Config.config.getAppInfo().getCaType() == 1) {
            throw new GlobalException(ResultInfoEnum.FUNCTION_CALL_ERROR);
        }

        String api = Config.config.getApi() + PathUtil.CITA_NODE_TRANS;
        nonce = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes());

        // Get current block height
        ResGetBlockHeight resGetBlockHeight = NodeService.getBlockHeight();
        long blockIndex = 0;
        if (resGetBlockHeight != null && !resGetBlockHeight.getData().isEmpty()) {
            blockIndex = Long.parseLong(resGetBlockHeight.getData()) + valid_until_block;
        } else {
            throw new GlobalException(ResultInfoEnum.BLOCK_HEIGHT_ERROR);
        }

        // Get FuncData
        String addFuncData = getAddFuncData(req);

        // Get the signed transaction
        String rawTx = getRawTx(req, blockIndex, addFuncData);

        // Send transaction
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
     * Get the signed transaction in string format
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
     * Get FuncData
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
                inputs.add(new Bytes32(Common.getByte32String(req.getArgs()[0])));
                inputs.add(new DynamicBytes(req.getArgs()[1].getBytes()));
                break;
            case "retrieve":
            case "remove":
                inputs.add(new Bytes32(Common.getByte32String(req.getArgs()[0])));
                break;
            case "keyAtIndex":
                inputs.add(new Uint256(Long.parseLong(req.getArgs()[0])));
                break;
            default:
        }
        if (inputs.size() == 0) {
            throw new GlobalException(ResultInfoEnum.FUNCTION_ERROR);
        }

        // Function
        Function addFunc = new Function(req.getFuncName(), inputs, Collections.emptyList());
        return FunctionEncoder.encode(addFunc);
    }
}
