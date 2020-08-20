package com.bsnbase.sdk.client.fabric.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.base.BaseResArrayModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fabric.ReqChainCodeQuery;
import com.bsnbase.sdk.entity.req.fabric.ReqChainCodeRegister;
import com.bsnbase.sdk.entity.req.fabric.ReqChainCodeRemove;
import com.bsnbase.sdk.entity.res.fabric.ResChainCodeQuery;
import com.bsnbase.sdk.entity.res.fabric.ResChainCodeRegister;
import com.bsnbase.sdk.entity.res.fabric.ResChainCodeRemove;
import com.bsnbase.sdk.util.common.HttpService;

import java.util.List;

public class ChainCodeService {

	
	
    /**
     * 链码事件注册
     * @param reqData
     */
	public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqData){
        String api =  Config.config.getApi() + "/api/fabric/v1/chainCode/event/register";
        BaseReqModel<ReqChainCodeRegister> req = new  BaseReqModel<ReqChainCodeRegister>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqChainCodeRegister,ResChainCodeRegister> httpService =new HttpService<ReqChainCodeRegister,ResChainCodeRegister>();
        BaseResModel<ResChainCodeRegister> res = httpService.post(req,api, ResChainCodeRegister.class);
        return res.getBody();
    }

    /**
     * 链码事件查询
     * @return
     */
	public static List<ResChainCodeQuery> eventQuery(){
        String api =  Config.config.getApi() + "/api/fabric/v1/chainCode/event/query";
        BaseReqModel<ReqChainCodeQuery> req = new  BaseReqModel<ReqChainCodeQuery>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqChainCodeQuery,ResChainCodeQuery> httpService =new HttpService<ReqChainCodeQuery,ResChainCodeQuery>();
        BaseResArrayModel<ResChainCodeQuery> res = httpService.arrayPost(req,api, ResChainCodeQuery.class);
        return res.getBody();
	}

    /**
     * 链码事件注销
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
        BaseResModel<ResChainCodeRemove> res = httpService.post(req,api, ResChainCodeRemove.class);
        return res.getBody();
	}
    
}
