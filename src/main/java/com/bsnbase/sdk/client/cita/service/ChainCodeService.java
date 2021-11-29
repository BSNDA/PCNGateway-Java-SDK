package com.bsnbase.sdk.client.cita.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResArrayModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.ReqChainCodeCancel;
import com.bsnbase.sdk.entity.req.cita.ReqChainCodeQuery;
import com.bsnbase.sdk.entity.req.cita.ReqChainCodeRegister;
import com.bsnbase.sdk.entity.resp.cita.ResChainCodeCancel;
import com.bsnbase.sdk.entity.resp.cita.ResChainCodeQuery;
import com.bsnbase.sdk.entity.resp.cita.ResChainCodeRegister;
import com.bsnbase.sdk.util.PathUtil.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;

import java.util.List;

public class ChainCodeService {

    /**
     * Chaincode event registration
     * You can register cita's block event or contract event through this interface, and when the event is triggered, the system will send the event content to the registered notification address.
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqData) {
        String api = Config.config.getApi() + PathUtil.CITA_EVENT_REGISTER;
        BaseReqModel<ReqChainCodeRegister> req = new BaseReqModel<>(reqData);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqChainCodeRegister, ResChainCodeRegister> httpService = new HttpService<>();
        BaseResModel<ResChainCodeRegister> res = httpService.post(req, api, ResChainCodeRegister.class);
        return res.getBody();
    }

    /**
     * Query chaincode event
     * This interface can query the list of registered events.
     */
    public static List<ResChainCodeQuery> eventQuery() {
        String api = Config.config.getApi() + PathUtil.CITA_EVENT_QUERY;
        BaseReqModel<ReqChainCodeQuery> req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqChainCodeQuery, ResChainCodeQuery> httpService = new HttpService<>();
        BaseResArrayModel<ResChainCodeQuery> res = httpService.arrayPost(req, api, ResChainCodeQuery.class);
        return res.getBody();
    }

    /**
     * Remove chaincode event
     * This interface can remove the registered events.
     */
    public static ResChainCodeCancel eventRemove(ReqChainCodeCancel reqData) {
        String api = Config.config.getApi() + PathUtil.CITA_EVENT_REMOVE;
        BaseReqModel<ReqChainCodeCancel> req = new BaseReqModel<>(reqData);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqChainCodeCancel, ResChainCodeCancel> httpService = new HttpService<>();
        BaseResModel<ResChainCodeCancel> res = httpService.post(req, api, ResChainCodeCancel.class);
        return res.getBody();
    }

}
