package com.bsnbase.sdk.client.cita.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.ReqUserRegister;
import com.bsnbase.sdk.entity.resp.cita.ResUserRegister;
import com.bsnbase.sdk.util.PathUtil.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;

import java.io.IOException;

public class UserService {
    /**
     * User registration
     * After a user participates in a CITA application service successfully, he/she needs to call this interface through the off-chain business system to generate the user account and account address for on-chain transaction processing.
     */
    public static ResUserRegister userRegister(ReqUserRegister register) throws IOException {
        String api = Config.config.getApi() + PathUtil.CITA_USER_REGISTER;
        BaseReqModel<ReqUserRegister> req = new BaseReqModel<ReqUserRegister>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(register);
        HttpService<ReqUserRegister, ResUserRegister> httpService = new HttpService<ReqUserRegister, ResUserRegister>();
        BaseResModel<ResUserRegister> res = httpService.post(req, api, ResUserRegister.class);
        ResUserRegister body = res.getBody();
        return body;
    }
}
