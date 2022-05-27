package com.bsnbase.sdk.client.fabric.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fabric.ReqKeyEscrowEnroll;
import com.bsnbase.sdk.entity.req.fabric.ReqUserRegister;
import com.bsnbase.sdk.entity.resp.fabric.ResKeyEscrowEnroll;
import com.bsnbase.sdk.entity.resp.fabric.ResUserRegister;
import com.bsnbase.sdk.util.path.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;
import com.bsnbase.sdk.util.common.StoreUtils;
import com.bsnbase.sdk.util.common.UserCertInfo;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class UserService {

    /**
     * User registration
     * <p>
     * In both key trust mode and public key upload mode, when the user is participating in the Fabric application and needs to create a separate user transaction certificate for the sub-user of the off-chain system, it is necessary to call this interface first to register the user in this city node, and the user's username is name@appCode in the call parameter.
     */
    public static ResUserRegister userRegister(ReqUserRegister register) throws IOException {
        String api = Config.config.getApi() + PathUtil.FABRIC_USER_REGISTER;
        BaseReqModel<ReqUserRegister> req = new BaseReqModel<ReqUserRegister>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(register);
        HttpService<ReqUserRegister, ResUserRegister> httpService = new HttpService<ReqUserRegister, ResUserRegister>();
        BaseResModel<ResUserRegister> res = httpService.post(req, api, ResUserRegister.class);

        ResUserRegister body = res.getBody();
        return body;
    }

    /**
     * User certificate registration in Public Key Upload Mode
     * When a user participated in the application in the public key upload mode needs to register a sub-user, after completing the user registration interface, he/she can call this interface to upload a public key certificate application file and obtain a sub-user certificate issued by the city node.
     * An exception will be returned when this interface is called in key trust mode.
     */
    public static ResKeyEscrowEnroll userEnroll(@NotNull ReqKeyEscrowEnroll kes) throws IOException {
        String api = Config.config.getApi() + PathUtil.FABRIC_USER_ENROLL;
        UserCertInfo certInfo = StoreUtils.generateCSR(kes.getName(), Config.config.getAppCode());
        kes.setCsrPem(certInfo.getCSRPem());

        BaseReqModel<ReqKeyEscrowEnroll> req = new BaseReqModel<ReqKeyEscrowEnroll>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(kes);

        HttpService<ReqKeyEscrowEnroll, ResKeyEscrowEnroll> httpService = new HttpService<ReqKeyEscrowEnroll, ResKeyEscrowEnroll>();
        BaseResModel<ResKeyEscrowEnroll> res = httpService.post(req, api, ResKeyEscrowEnroll.class);

        ResKeyEscrowEnroll body = res.getBody();

        //Save the private key
        Config.config.getKeyStore().storeUserPrivateKey(kes.getName(), Config.config.getAppCode(), certInfo.getKey());
        //Save the registered certificate
        Config.config.getKeyStore().storeUserCert(kes.getName(), Config.config.getAppCode(), body.getCert());

        return body;
    }

}
