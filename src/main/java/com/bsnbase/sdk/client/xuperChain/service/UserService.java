package com.bsnbase.sdk.client.xuperChain.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.ReqUserRegister;
import com.bsnbase.sdk.entity.res.xuperChain.ResUserRegister;
import com.bsnbase.sdk.util.common.HttpService;

import java.io.IOException;

public class UserService {
    /**
     * 用户注册
     *
     * @param register
     * @return
     * @throws IOException
     */
    public static ResUserRegister userRegister(ReqUserRegister register) throws IOException {
        String api = Config.config.getApi() + "/api/xuperchain/v1/user/register";
        BaseReqModel<ReqUserRegister> req = new BaseReqModel<>(register);
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqUserRegister, ResUserRegister> httpService = new HttpService<ReqUserRegister, ResUserRegister>();
        BaseResModel<ResUserRegister> res = httpService.post(req, api, Config.config.getCert(), ResUserRegister.class);
        ResUserRegister body = res.getBody();
        return body;
    }
}
