package com.bsnbase.sdk.client.fiscobcos.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResArrayModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqChainCodeCancel;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqChainCodeQuery;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqChainCodeRegister;
import com.bsnbase.sdk.entity.res.fiscobcos.ResChainCodeCancel;
import com.bsnbase.sdk.entity.res.fiscobcos.ResChainCodeQuery;
import com.bsnbase.sdk.entity.res.fiscobcos.ResChainCodeRegister;
import com.bsnbase.sdk.util.common.HttpService;

import java.util.List;

public class ChainCodeService {

    /**
     * 链码事件注册
     *
     * @param reqData
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqData) {
        String api = Config.config.getApi() + "/api/fiscobcos/v1/event/register";
        BaseReqModel<ReqChainCodeRegister> req = new BaseReqModel<ReqChainCodeRegister>(reqData);
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqChainCodeRegister, ResChainCodeRegister> httpService = new HttpService<ReqChainCodeRegister, ResChainCodeRegister>();
        BaseResModel<ResChainCodeRegister> res = httpService.post(req, api, Config.config.getCert(), ResChainCodeRegister.class);
        return res.getBody();
    }

    /**
     * 链码事件查询
     *
     * @return
     */
    public static List<ResChainCodeQuery> eventQuery() throws Exception {
        String api = Config.config.getApi() + "/api/fiscobcos/v1/event/query";
        BaseReqModel<ReqChainCodeQuery> req = new BaseReqModel<ReqChainCodeQuery>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqChainCodeQuery, ResChainCodeQuery> httpService = new HttpService<ReqChainCodeQuery, ResChainCodeQuery>();
        BaseResArrayModel<ResChainCodeQuery> res = httpService.arrayPost(req, api, Config.config.getCert(), ResChainCodeQuery.class);
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
        String api = Config.config.getApi() + "/api/fiscobcos/v1/event/remove";
        BaseReqModel<ReqChainCodeCancel> req = new BaseReqModel<>(reqData);
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqChainCodeCancel, ResChainCodeCancel> httpService = new HttpService<>();
        BaseResModel<ResChainCodeCancel> res = httpService.post(req, api, Config.config.getCert(), ResChainCodeCancel.class);
        return res.getBody();
    }

}
