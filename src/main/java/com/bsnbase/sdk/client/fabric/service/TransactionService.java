package com.bsnbase.sdk.client.fabric.service;


import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fabric.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.fabric.ReqKeyEscrowNo;
import com.bsnbase.sdk.entity.resp.fabric.ResKeyEscrow;
import com.bsnbase.sdk.entity.resp.fabric.ResKeyEscrowNo;
import com.bsnbase.sdk.entity.transactionHeader.TransactionRequest;
import com.bsnbase.sdk.entity.transactionHeader.TransactionUser;
import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;
import com.bsnbase.sdk.util.path.PathUtil;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.common.HttpService;
import com.bsnbase.sdk.util.common.Nonce;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static com.bsnbase.sdk.util.common.TransData.getTransdata;

public class TransactionService {

    /**
     * Invoke chaincode in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description.
     * After invoking the gateway, the gateway will return the execution result of the chaincode.
     * This interface will directly response the result and does not wait for the transaction to block. User can call "Retrive Transaction Information API" to query the block based on the transaction ID.；
     */
    public static ResKeyEscrow reqChainCode(@NotNull ReqKeyEscrow kes) throws IOException {
        String api = Config.config.getApi() + PathUtil.FABRIC_NODE_REQ_CHAIN_CODE;
        BaseReqModel<ReqKeyEscrow> req = new BaseReqModel<ReqKeyEscrow>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        kes.setNonce(Nonce.getNonceString());
        req.setBody(kes);

        HttpService<ReqKeyEscrow, ResKeyEscrow> httpService = new HttpService<ReqKeyEscrow, ResKeyEscrow>();
        BaseResModel<ResKeyEscrow> res = httpService.post(req, api, ResKeyEscrow.class);
        return res.getBody();
    }

    /**
     * Invoke chaincode in Public Key Upload Mode
     * <p>
     * When the user of the public key upload mode application needs to initiate a transaction from the off-chain system to the chaincode on the chain, he/she needs to assemble the transaction message locally and call this interface to initiate the transaction.
     */
    public static ResKeyEscrowNo nodeTrans(@NotNull ReqKeyEscrow reqkey) throws NoSuchAlgorithmException {
        String api = Config.config.getApi() + PathUtil.FABRIC_NODE_TRANS;

        TransactionUser user = null;
        try {
            user = Config.config.getKeyStore().loadUser(reqkey.getUserName(), Config.config.getAppCode());
            user.setMspId(Config.config.getAppInfo().getMspId());
            AlgorithmTypeEnum algorithmTypeEnum = AlgorithmTypeEnum.fromAlgorithmTypeEnum(Config.config.getAppInfo().getAlgorithmType());
            if (algorithmTypeEnum==AlgorithmTypeEnum.AppAlgorithmType_SM2 && Config.config.getAppInfo().getFabricVersion().equals("2.2.1")){
                user.setSM3(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.USER_CERTIFICATE_ERROR.getMsg());
        }
        //Assemble the transaction information
        TransactionRequest request = new TransactionRequest();
        request.setChannelId(Config.config.getAppInfo().getChannelId());
        request.setArgs(Common.StringBytesConvert(reqkey.getArgs()));
        request.setTransientMap(reqkey.getTransientData());
        request.setChaincodeId(reqkey.getChainCode());
        request.setFcn(reqkey.getFuncName());

        String transData = null;
        try {
            transData = getTransdata(request, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.TRANSACTION_CONVERSION_ERROR.getMsg());
        }

        ReqKeyEscrowNo keyNo = new ReqKeyEscrowNo();
        keyNo.setTransData(transData);
        BaseReqModel<ReqKeyEscrowNo> req = new BaseReqModel<ReqKeyEscrowNo>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(keyNo);
        HttpService<ReqKeyEscrowNo, ResKeyEscrowNo> httpService = new HttpService<ReqKeyEscrowNo, ResKeyEscrowNo>();
        BaseResModel<ResKeyEscrowNo> res = httpService.post(req, api, ResKeyEscrowNo.class);

        return res.getBody();

    }


}
