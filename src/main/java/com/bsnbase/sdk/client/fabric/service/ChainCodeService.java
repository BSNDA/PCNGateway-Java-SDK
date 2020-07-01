package com.bsnbase.sdk.client.fabric.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.base.BaseResArrayModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.ReqChainCodeQuery;
import com.bsnbase.sdk.entity.req.ReqChainCodeRegister;
import com.bsnbase.sdk.entity.req.ReqChainCodeRemove;
import com.bsnbase.sdk.entity.res.ResChainCodeQuery;
import com.bsnbase.sdk.entity.res.ResChainCodeRegister;
import com.bsnbase.sdk.entity.res.ResChainCodeRemove;
import com.bsnbase.sdk.util.common.HttpService;

import java.util.List;

public class ChainCodeService {

	
	
    /**
     * register event chaincode
     * @param reqData
     */
	public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqData){
        String api =  Config.config.getApi() + "/api/fabric/v1/chainCode/event/register";
        BaseReqModel<ReqChainCodeRegister> req = new  BaseReqModel<ReqChainCodeRegister>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqData);
        //req.sign();
        HttpService<ReqChainCodeRegister,ResChainCodeRegister> httpService =new HttpService<ReqChainCodeRegister,ResChainCodeRegister>();
        BaseResModel<ResChainCodeRegister> res = httpService.post(req,api, Config.config.getCert(),ResChainCodeRegister.class);
        return res.getBody();
    }

    /**
     * event chaincode query 
     * @return
     */
	public static List<ResChainCodeQuery> eventQuery(){
        String api =  Config.config.getApi() + "/api/fabric/v1/chainCode/event/query";
        BaseReqModel<ReqChainCodeQuery> req = new  BaseReqModel<ReqChainCodeQuery>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqChainCodeQuery,ResChainCodeQuery> httpService =new HttpService<ReqChainCodeQuery,ResChainCodeQuery>();


        BaseResArrayModel<ResChainCodeQuery> res = httpService.arrayPost(req,api, Config.config.getCert(),ResChainCodeQuery.class);


        return res.getBody();
	}

    /**
     * logout event chaincode 
     * @param reqData
     * @return
     * @throws Exception
     */
	public static ResChainCodeRemove eventRemove(ReqChainCodeRemove reqData){
        String api =  Config.config.getApi() + "/api/fabric/v1/chainCode/event/remove";
        BaseReqModel<ReqChainCodeRemove> req = new  BaseReqModel<ReqChainCodeRemove>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqChainCodeRemove,ResChainCodeRemove> httpService =new HttpService<ReqChainCodeRemove,ResChainCodeRemove>();
        BaseResModel<ResChainCodeRemove> res = httpService.post(req,api, Config.config.getCert(),ResChainCodeRemove.class);
        return res.getBody();
	}
    
}
