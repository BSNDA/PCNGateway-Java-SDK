package com.bsnbase.sdk.client.cita.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResArrayModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.ReqChainCodeCancel;
import com.bsnbase.sdk.entity.req.cita.ReqChainCodeQuery;
import com.bsnbase.sdk.entity.req.cita.ReqChainCodeRegister;
import com.bsnbase.sdk.entity.res.cita.ResChainCodeCancel;
import com.bsnbase.sdk.entity.res.cita.ResChainCodeQuery;
import com.bsnbase.sdk.entity.res.cita.ResChainCodeRegister;
import com.bsnbase.sdk.util.common.HttpService;

import java.util.List;

public class ChainCodeService {

    /**
     * 链码事件注册
     *
     * @param reqData
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqData) {
        String api = Config.config.getApi() + "/api/cita/v1/event/register";
        BaseReqModel<ReqChainCodeRegister> req = new BaseReqModel<>(reqData);
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqChainCodeRegister, ResChainCodeRegister> httpService = new HttpService<>();
        BaseResModel<ResChainCodeRegister> res = httpService.post(req, api,  ResChainCodeRegister.class);
        return res.getBody();
    }

    /**
     * 链码事件查询
     *
     * @return
     */
    public static List<ResChainCodeQuery> eventQuery() {
        String api = Config.config.getApi() + "/api/cita/v1/event/query";
        BaseReqModel<ReqChainCodeQuery> req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqChainCodeQuery, ResChainCodeQuery> httpService = new HttpService<>();
        BaseResArrayModel<ResChainCodeQuery> res = httpService.arrayPost(req, api,  ResChainCodeQuery.class);
        return res.getBody();
    }

    /**
     * 链码事件取消接口
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    public static ResChainCodeCancel eventRemove(ReqChainCodeCancel reqData) {
        String api = Config.config.getApi() + "/api/cita/v1/event/remove";
        BaseReqModel<ReqChainCodeCancel> req = new BaseReqModel<>(reqData);
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqChainCodeCancel, ResChainCodeCancel> httpService = new HttpService<>();
        BaseResModel<ResChainCodeCancel> res = httpService.post(req, api,  ResChainCodeCancel.class);
        return res.getBody();
    }

}
