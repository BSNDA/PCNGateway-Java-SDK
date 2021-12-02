package com.bsnbase.sdk.client.fiscobcos.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqUserRegister;
import com.bsnbase.sdk.entity.resp.fiscobcos.ResUserRegister;
import com.bsnbase.sdk.util.path.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;

import java.io.IOException;

public class UserService {
    /**
     * User registration
     * <p>
     * In both key trust mode and public key upload mode, when the user participated in the FISCO BCOS application needs to create a separate user transaction certificate for the sub-user of the off-chain system, it is necessary to call this interface first to register the sub-user in this city node, and the sub-user's username is name@appCode in the call parameter.
     */
    public static ResUserRegister userRegister(ReqUserRegister register) throws IOException {
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_USER_REGISTER;
        BaseReqModel<ReqUserRegister> req = new BaseReqModel<>(register);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqUserRegister, ResUserRegister> httpService = new HttpService<ReqUserRegister, ResUserRegister>();
        BaseResModel<ResUserRegister> res = httpService.post(req, api, ResUserRegister.class);
        ResUserRegister body = res.getBody();
        return body;
    }
}
