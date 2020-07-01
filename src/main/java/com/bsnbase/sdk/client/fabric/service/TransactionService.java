package com.bsnbase.sdk.client.fabric.service;


import static com.bsnbase.sdk.util.common.TransData.getTransdata;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.exception.GlobalException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.ReqKeyEscrowNo;
import com.bsnbase.sdk.entity.res.ResKeyEscrow;
import com.bsnbase.sdk.entity.res.ResKeyEscrowNo;
import com.bsnbase.sdk.entity.transactionHeader.TransactionRequest;
import com.bsnbase.sdk.entity.transactionHeader.TransactionUser;
import com.bsnbase.sdk.util.common.HttpService;
import com.bsnbase.sdk.util.sign.Nonce;

@Service
public class TransactionService {

    /**
     * transaction processing under key management mode 
     * @param kes
     * @return
     * @throws IOException
     */
    public static ResKeyEscrow reqChainCode(@NotNull ReqKeyEscrow kes) throws IOException{
    	 String api =  Config.config.getApi() + "/api/fabric/v1/node/reqChainCode";
         BaseReqModel<ReqKeyEscrow> req = new  BaseReqModel<ReqKeyEscrow>();
         req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
         kes.setNonce(Nonce.getNonceString());
         System.out.println(kes.getEncryptionValue());
         req.setBody(kes);

         HttpService<ReqKeyEscrow,ResKeyEscrow> httpService =new HttpService<ReqKeyEscrow,ResKeyEscrow>();
         BaseResModel<ResKeyEscrow> res = httpService.post(req,api, Config.config.getCert(),ResKeyEscrow.class);
         return res.getBody();

    }

    /**
     * transaction under key unmanagment mode 
     * @param reqkey
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException 
     */
    public static ResKeyEscrowNo nodeTrans(@NotNull ReqKeyEscrow reqkey) throws  NoSuchAlgorithmException{
    	String api =  Config.config.getApi() + "/api/fabric/v1/node/trans";

        TransactionUser user = null;
        try {
            user = Config.config.getKeyStore().loadUser(reqkey.getUserName(),Config.config.getAppCode());
            user.setMspId(Config.config.getAppInfo().getMspId());
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException("failed to get user cert");
        }

        TransactionRequest request = new TransactionRequest();
        request.setChanelId(Config.config.getAppInfo().getChannelId());
        request.setArgs(Common.StringBytesConvert(reqkey.getArgs()));
        request.setTransientMap(reqkey.getTransientData());
        request.setChaincodeId(reqkey.getChainCode());
        request.setFcn(reqkey.getFuncName());

        String transData = null;
        try {
            transData = getTransdata(request, user);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException("failed to convert transaction data");
        }

        ReqKeyEscrowNo keyNo = new ReqKeyEscrowNo();
        keyNo.setTransData(transData);
        BaseReqModel<ReqKeyEscrowNo> req = new  BaseReqModel<ReqKeyEscrowNo>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(keyNo);
        HttpService<ReqKeyEscrowNo,ResKeyEscrowNo> httpService =new HttpService<ReqKeyEscrowNo,ResKeyEscrowNo>();
        BaseResModel<ResKeyEscrowNo> res = httpService.post(req,api, Config.config.getCert(),ResKeyEscrowNo.class);

        return res.getBody();

    }



}
