package com.bsnbase.sdk.client.xuperChain.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.ReqUserRegister;
import com.bsnbase.sdk.entity.resp.xuperChain.ResUserRegister;
import com.bsnbase.sdk.util.path.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;

public class UserService {
    /**
     * User registration
     * After a user participates in a XuperChain-based application, he/she needs to call this interface through the off-chain business system to generate the user account and account address for on-chain transaction processing.
     */
    public static ResUserRegister userRegister(ReqUserRegister register) {
        String api = Config.config.getApi() + PathUtil.XUPERCHAIN_USER_REGISTER;
        BaseReqModel<ReqUserRegister> req = new BaseReqModel<>(register);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqUserRegister, ResUserRegister> httpService = new HttpService<ReqUserRegister, ResUserRegister>();
        BaseResModel<ResUserRegister> res = httpService.post(req, api, ResUserRegister.class);
        ResUserRegister body = res.getBody();
        return body;
    }
}
