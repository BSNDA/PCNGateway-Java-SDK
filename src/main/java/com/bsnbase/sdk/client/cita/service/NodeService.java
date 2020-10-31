package com.bsnbase.sdk.client.cita.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.ReqGetBlockInformation;
import com.bsnbase.sdk.entity.req.cita.ReqGetTransaction;
import com.bsnbase.sdk.entity.req.cita.ReqGetTxReceiptByTxHash;
import com.bsnbase.sdk.entity.res.cita.ResGetBlockHeight;
import com.bsnbase.sdk.entity.res.cita.ResGetBlockInformation;
import com.bsnbase.sdk.entity.res.cita.ResGetTransaction;
import com.bsnbase.sdk.entity.res.cita.ResGetTxReceiptByTxHash;
import com.bsnbase.sdk.util.common.HttpService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class NodeService {

    /**
     * 获取交易回执接口
     *
     * @return ResGetTxReceiptByTxHash
     */
    public static ResGetTxReceiptByTxHash getTxReceiptByTxHash(ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash) {
        String api = Config.config.getApi() + "/api/cita/v1/node/getTxReceiptByTxHash";
        BaseReqModel<ReqGetTxReceiptByTxHash> req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqGetTxReceiptByTxHash);
        HttpService<ReqGetTxReceiptByTxHash, ResGetTxReceiptByTxHash> httpService = new HttpService();
        BaseResModel<ResGetTxReceiptByTxHash> res = httpService.post(req, api,  ResGetTxReceiptByTxHash.class);
        return res.getBody();
    }
   
	/**
     * 获取交易信息
     * @param reqData
     */
	public static ResGetTransaction getTransaction(ReqGetTransaction reqData){
        String api =  Config.config.getApi() + "/api/cita/v1/node/getTxInfoByTxHash";
        BaseReqModel<ReqGetTransaction> req = new  BaseReqModel<ReqGetTransaction>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqGetTransaction,ResGetTransaction> httpService =new HttpService<ReqGetTransaction,ResGetTransaction>();
        BaseResModel<ResGetTransaction> res = httpService.post(req,api, ResGetTransaction.class);

        return res.getBody();
	}

    /**
     * 获取块信息
     * @param reqData
     */
	public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqData){
        String api =  Config.config.getApi() + "/api/cita/v1/node/getBlockInfo";
        BaseReqModel<ReqGetBlockInformation> req = new  BaseReqModel<ReqGetBlockInformation>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqGetBlockInformation,ResGetBlockInformation> httpService =new HttpService<ReqGetBlockInformation,ResGetBlockInformation>();
        BaseResModel<ResGetBlockInformation> res = httpService.post(req,api, ResGetBlockInformation.class);
         return res.getBody();
	}


    /**
     * 获取应用内的块高接口
     */
    public static ResGetBlockHeight getBlockHeight() {
        String api = Config.config.getApi() + "/api/cita/v1/node/getBlockHeight";
        BaseReqModel req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService httpService = new HttpService<>();
        BaseResModel<ResGetBlockHeight> res = httpService.post(req, api,  ResGetBlockHeight.class);
        return res.getBody();
    }


}
