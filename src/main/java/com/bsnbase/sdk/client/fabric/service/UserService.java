package com.bsnbase.sdk.client.fabric.service;

import java.io.IOException;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fabric.ReqKeyEscrowEnroll;
import com.bsnbase.sdk.entity.req.fabric.ReqUserRegister;
import com.bsnbase.sdk.entity.res.fabric.ResKeyEscrowEnroll;
import com.bsnbase.sdk.entity.res.fabric.ResUserRegister;
import com.bsnbase.sdk.util.common.HttpService;
import com.bsnbase.sdk.util.common.StoreUtils;
import com.bsnbase.sdk.util.common.UserCertInfo;
import org.jetbrains.annotations.NotNull;

public class UserService {
    /**
     * 用户注册
     *
     * @param register
     * @return
     * @throws IOException
     */
    public static ResUserRegister userRegister(ReqUserRegister register) throws IOException {
        String api = Config.config.getApi() + "/api/fabric/v1/user/register";
        BaseReqModel<ReqUserRegister> req = new BaseReqModel<ReqUserRegister>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(register);
        HttpService<ReqUserRegister, ResUserRegister> httpService = new HttpService<ReqUserRegister, ResUserRegister>();
        BaseResModel<ResUserRegister> res = httpService.post(req, api, Config.config.getCert(), ResUserRegister.class);

        ResUserRegister body = res.getBody();
        return body;
    }

    /**
     * 用户非托管模式用户证书登记
     *
     * @param kes
     * @return
     * @throws IOException
     */
    public static ResKeyEscrowEnroll userEnroll(@NotNull ReqKeyEscrowEnroll kes) throws IOException {
        String api = Config.config.getApi() + "/api/fabric/v1/user/enroll";
        UserCertInfo certInfo = StoreUtils.generateCSR(kes.getName(), Config.config.getAppCode(), Config.config.getMspDir());
        System.out.println(certInfo.getCSRPem());
        kes.setCsrPem(certInfo.getCSRPem());

        BaseReqModel<ReqKeyEscrowEnroll> req = new BaseReqModel<ReqKeyEscrowEnroll>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(kes);

        HttpService<ReqKeyEscrowEnroll, ResKeyEscrowEnroll> httpService = new HttpService<ReqKeyEscrowEnroll, ResKeyEscrowEnroll>();
        BaseResModel<ResKeyEscrowEnroll> res = httpService.post(req, api, Config.config.getCert(), ResKeyEscrowEnroll.class);

        ResKeyEscrowEnroll body = res.getBody();

        //存储私钥
        Config.config.getKeyStore().storeUserPrivateKey(kes.getName(), Config.config.getAppCode(), certInfo.getKey());
        //存储登记的证书
        Config.config.getKeyStore().storeUserCert(kes.getName(), Config.config.getAppCode(), body.getCert());

        return body;
    }

}
