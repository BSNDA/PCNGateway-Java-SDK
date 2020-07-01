package com.bsnbase.sdk.client.fabric.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.ReqGetBlockInformation;
import com.bsnbase.sdk.entity.req.ReqGetLedgerInfo;
import com.bsnbase.sdk.entity.req.ReqGetTransaction;
import com.bsnbase.sdk.entity.res.ResGetBlockInformation;
import com.bsnbase.sdk.entity.res.ResGetLedgerInfo;
import com.bsnbase.sdk.entity.res.ResGetTransaction;
import com.bsnbase.sdk.entity.res.ResUserRegister;
import com.bsnbase.sdk.util.common.HttpService;

public class NodeService {
   
	/**
     * get transaction data 
     * @param reqData
     */
	public static ResGetTransaction getTransaction(ReqGetTransaction reqData){
        String api =  Config.config.getApi() + "/api/fabric/v1/node/getTransaction";
        BaseReqModel<ReqGetTransaction> req = new  BaseReqModel<ReqGetTransaction>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqGetTransaction,ResGetTransaction> httpService =new HttpService<ReqGetTransaction,ResGetTransaction>();
        BaseResModel<ResGetTransaction> res = httpService.post(req,api, Config.config.getCert(),ResGetTransaction.class);

        return res.getBody();
	}

    /**
     * get block data 
     * @param reqData
     */
	public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqData){
        String api =  Config.config.getApi() + "/api/fabric/v1/node/getBlockInfo";
        BaseReqModel<ReqGetBlockInformation> req = new  BaseReqModel<ReqGetBlockInformation>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqGetBlockInformation,ResGetBlockInformation> httpService =new HttpService<ReqGetBlockInformation,ResGetBlockInformation>();
        BaseResModel<ResGetBlockInformation> res = httpService.post(req,api, Config.config.getCert(),ResGetBlockInformation.class);
         return res.getBody();
	}

    /**
     * get the latest ledger data 
     */
	
	public static ResGetLedgerInfo getLedgerInfo(){
        String api =  Config.config.getApi() + "/api/fabric/v1/node/getLedgerInfo";
        BaseReqModel<ReqGetLedgerInfo> req = new  BaseReqModel<ReqGetLedgerInfo>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqGetLedgerInfo,ResGetLedgerInfo> httpService =new HttpService<ReqGetLedgerInfo,ResGetLedgerInfo>();
        BaseResModel<ResGetLedgerInfo> res = httpService.post(req,api, Config.config.getCert(),ResGetLedgerInfo.class);

        return res.getBody();
	}

}
