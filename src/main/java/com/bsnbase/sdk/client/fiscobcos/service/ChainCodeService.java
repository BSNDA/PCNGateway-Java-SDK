package com.bsnbase.sdk.client.fiscobcos.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResArrayModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqChainCodeCancel;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqChainCodeQuery;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqChainCodeRegister;
import com.bsnbase.sdk.entity.resp.fiscobcos.ResChainCodeCancel;
import com.bsnbase.sdk.entity.resp.fiscobcos.ResChainCodeQuery;
import com.bsnbase.sdk.entity.resp.fiscobcos.ResChainCodeRegister;
import com.bsnbase.sdk.util.path.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;

import java.util.List;

public class ChainCodeService {

    /**
     * Chaincode event registration
     * You can register FISCO-BCOS's block event or contract event through this interface, and when the event is triggered, the system will send the event content to the registered notification address.
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqData) {
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_EVENT_REGISTER;
        BaseReqModel<ReqChainCodeRegister> req = new BaseReqModel<ReqChainCodeRegister>(reqData);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqChainCodeRegister, ResChainCodeRegister> httpService = new HttpService<ReqChainCodeRegister, ResChainCodeRegister>();
        BaseResModel<ResChainCodeRegister> res = httpService.post(req, api, ResChainCodeRegister.class);
        return res.getBody();
    }

    /**
     * Query chaincode event
     * This interface can query the list of registered chaincode events and block events.
     */
    public static List<ResChainCodeQuery> eventQuery() throws Exception {
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_EVENT_QUERY;
        BaseReqModel<ReqChainCodeQuery> req = new BaseReqModel<ReqChainCodeQuery>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqChainCodeQuery, ResChainCodeQuery> httpService = new HttpService<ReqChainCodeQuery, ResChainCodeQuery>();
        BaseResArrayModel<ResChainCodeQuery> res = httpService.arrayPost(req, api, ResChainCodeQuery.class);
        return res.getBody();
    }

    /**
     * Remove chaincode event
     * This interface can remove the registered chaincode events and block events.
     */
    public static ResChainCodeCancel eventRemove(ReqChainCodeCancel reqData) {
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_EVENT_REMOVE;
        BaseReqModel<ReqChainCodeCancel> req = new BaseReqModel<>(reqData);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqChainCodeCancel, ResChainCodeCancel> httpService = new HttpService<>();
        BaseResModel<ResChainCodeCancel> res = httpService.post(req, api, ResChainCodeCancel.class);
        return res.getBody();
    }

}
