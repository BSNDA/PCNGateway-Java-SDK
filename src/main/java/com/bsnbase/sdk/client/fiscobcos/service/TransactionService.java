package com.bsnbase.sdk.client.fiscobcos.service;


import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqGetTxCountByBlockNumber;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqGetTxReceiptByTxHash;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqKeyEscrow;
import com.bsnbase.sdk.entity.res.fiscobcos.ResGetTxCount;
import com.bsnbase.sdk.entity.res.fiscobcos.ResGetTxCountByBlockNumber;
import com.bsnbase.sdk.entity.res.fiscobcos.ResGetTxReceiptByTxHash;
import com.bsnbase.sdk.entity.res.fiscobcos.ResKeyEscrow;
import com.bsnbase.sdk.util.common.HttpService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public class TransactionService {

    /**
     * 密钥托管模式下调用智能合约接口
     * @param kes
     * @return
     * @throws IOException
     */
    public static ResKeyEscrow reqChainCode(@NotNull ReqKeyEscrow kes) throws IOException{
    	 String api =  Config.config.getApi() + "/api/fiscobcos/v1/node/reqChainCode";
         BaseReqModel<ReqKeyEscrow> req = new  BaseReqModel<ReqKeyEscrow>();
//         kes.setNonce(Nonce.getNonceString());
         System.out.println(kes.getEncryptionValue());
         req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
         req.setBody(kes);
         HttpService<ReqKeyEscrow,ResKeyEscrow> httpService =new HttpService<ReqKeyEscrow,ResKeyEscrow>();
         BaseResModel<ResKeyEscrow> res = httpService.post(req,api, Config.config.getCert(),ResKeyEscrow.class);
         return res.getBody();

    }


    /**
     * 获取应用内的交易总数
     * @return
     * @throws IOException
     */
    public static ResGetTxCount getTxCount() throws IOException{
        String api =  Config.config.getApi() + "/api/fiscobcos/v1/node/getTxCount";
        BaseReqModel<ReqKeyEscrow> req = new  BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService httpService =new HttpService();
        BaseResModel<ResGetTxCount> res = httpService.post(req,api, Config.config.getCert(),ResGetTxCount.class);
        return res.getBody();

    }

    /**
     * 获取块内的交易总数接口
     * @return
     * @throws IOException
     */
    public static ResGetTxCountByBlockNumber getTxCountByBlockNumber(@NotNull ReqGetTxCountByBlockNumber reqGetTxCountByBlockNumber) throws IOException{
        String api =  Config.config.getApi() + "/api/fiscobcos/v1/node/getTxCountByBlockNumber";
        BaseReqModel<ReqGetTxCountByBlockNumber> req = new  BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqGetTxCountByBlockNumber);
        HttpService<ReqGetTxCountByBlockNumber, ResGetTxCountByBlockNumber> httpService =new HttpService();
        BaseResModel<ResGetTxCountByBlockNumber> res = httpService.post(req,api, Config.config.getCert(),ResGetTxCountByBlockNumber.class);
        return res.getBody();
    }

    /**
     * 获取交易回执接口
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException 
     */
    public static ResGetTxReceiptByTxHash getTxReceiptByTxHash(ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash) throws  NoSuchAlgorithmException{
        String api =  Config.config.getApi() + "/api/fiscobcos/v1/node/getTxReceiptByTxHash";
        BaseReqModel<ReqGetTxReceiptByTxHash> req = new  BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqGetTxReceiptByTxHash);
        HttpService<ReqGetTxReceiptByTxHash, ResGetTxReceiptByTxHash> httpService =new HttpService();
        BaseResModel<ResGetTxReceiptByTxHash> res = httpService.post(req,api, Config.config.getCert(),ResGetTxReceiptByTxHash.class);
        return res.getBody();
    }
}
