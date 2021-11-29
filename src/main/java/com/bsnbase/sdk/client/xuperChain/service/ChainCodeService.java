package com.bsnbase.sdk.client.xuperChain.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResArrayModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.ReqChainCodeCancel;
import com.bsnbase.sdk.entity.req.xuperChain.ReqChainCodeQuery;
import com.bsnbase.sdk.entity.req.xuperChain.ReqChainCodeRegister;
import com.bsnbase.sdk.entity.resp.xuperChain.ResChainCodeCancel;
import com.bsnbase.sdk.entity.resp.xuperChain.ResChainCodeQuery;
import com.bsnbase.sdk.entity.resp.xuperChain.ResChainCodeRegister;
import com.bsnbase.sdk.util.PathUtil.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;

import java.util.List;

public class ChainCodeService {

    /**
     * Chaincode event registration
     * If the user who has participated in the application needs to trigger the off-chain business system for subsequent business processing by the chaincode event, he/she can call this interface to register the chaincode event to be listened to.
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqData) {
        String api = Config.config.getApi() + PathUtil.XUPERCHAIN_EVENT_REGISTER;
        BaseReqModel<ReqChainCodeRegister> req = new BaseReqModel<ReqChainCodeRegister>(reqData);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqChainCodeRegister, ResChainCodeRegister> httpService = new HttpService<ReqChainCodeRegister, ResChainCodeRegister>();
        BaseResModel<ResChainCodeRegister> res = httpService.post(req, api, ResChainCodeRegister.class);
        return res.getBody();
    }

    /**
     * Query chaincode event
     * This interface can query the list of registered events.
     */
    public static List<ResChainCodeQuery> eventQuery() {
        String api = Config.config.getApi() + PathUtil.XUPERCHAIN_EVENT_QUERY;
        BaseReqModel<ReqChainCodeQuery> req = new BaseReqModel<ReqChainCodeQuery>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqChainCodeQuery, ResChainCodeQuery> httpService = new HttpService<ReqChainCodeQuery, ResChainCodeQuery>();
        BaseResArrayModel<ResChainCodeQuery> res = httpService.arrayPost(req, api, ResChainCodeQuery.class);
        return res.getBody();
    }

    /**
     * Remove chaincode event
     * This interface can remove the registered events.
     */
    public static ResChainCodeCancel eventRemove(ReqChainCodeCancel reqData) {
        String api = Config.config.getApi() + PathUtil.XUPERCHAIN_EVENT_REMOVE;
        BaseReqModel<ReqChainCodeCancel> req = new BaseReqModel<>(reqData);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqChainCodeCancel, ResChainCodeCancel> httpService = new HttpService<>();
        BaseResModel<ResChainCodeCancel> res = httpService.post(req, api, ResChainCodeCancel.class);
        return res.getBody();
    }

}
