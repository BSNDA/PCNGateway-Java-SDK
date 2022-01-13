package com.bsnbase.sdk.entity.config;

import com.alibaba.fastjson.JSONObject;
import com.bsnbase.sdk.client.fabric.service.AppService;
import com.bsnbase.sdk.entity.resp.fabric.ResUserInfo;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.enums.AppTypeEnum;
import com.bsnbase.sdk.util.enums.CaTypeEnum;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import com.bsnbase.sdk.util.keystore.IKeyStore;
import com.bsnbase.sdk.util.keystore.KeyStore;
import com.google.common.base.Preconditions;
import lombok.Data;

import java.io.IOException;

/**
 * User configuration information
 */
@Data
public class Config {
    public static Config config;
    /**
     * Node gateway Url
     */
    String api;
    /**
     * Usercode
     */
    String userCode;
    /**
     * Appcode
     */
    String appCode;
    /**
     * User's public key
     */
    String puk;
    /**
     * User's private key
     */
    String prk;
    /**
     * Certificate storage directory
     */
    String mspDir;
    /**
     * BSN gateway public key configuration identity
     * true：Testnet services
     * false：Product environment services
     */
    boolean testServerIdn;
    /**
     * APP Info
     */
    ResUserInfo appInfo;
    /**
     * Storage processing of sub-user certificate
     */
    IKeyStore keyStore;

    /**
     * Read the local configuration file
     *
     * @param filePath
     * @return
     */
    public static Config buildLocalConfigByJson(String filePath) {
        String result = "";
        try {
            result = Common.readLocalFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.CONFIG_NOT_EXISTS);
        }
        Config config = buildConfgiByJsonStr(result);
        return config;
    }

    /**
     * Read the project configuration in the file under the directory of resource
     *
     * @param filePath
     * @return
     */
    public static Config buildByConfigJson(String filePath) {
        String result = "";
        try {
            result = Common.readFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.CONFIG_NOT_EXISTS);
        }
        Config config = buildConfgiByJsonStr(result);

        return config;
    }

    ;

    private static Config buildConfgiByJsonStr(String result) {
        JsonConfig config = JSONObject.parseObject(result, JsonConfig.class);
        Config cg = new Config();
        cg.setAppCode(config.getAppCode());
        cg.setUserCode(config.getUserCode());
        cg.setApi(config.getNodeApi());
        cg.setPuk(config.getBsnPublicKey());
        cg.setPrk(config.getUserPrivateKey());
        cg.setMspDir(config.getMspPath());
        return cg;
    }

    public void initConfig(Config cg) {
        if (config == null) {
            valid(cg);
            config = cg;
            keyStore = new KeyStore(config.getMspDir());
            ResUserInfo res = null;
            try {
                res = AppService.getAppInfo();
            } catch (IOException e) {
                e.printStackTrace();
                throw new GlobalException(ResultInfoEnum.GET_APP_INFO_ERROR);
            }
            if(CaTypeEnum.NON_HOSTED.nCode==res.getCaType()&&AppTypeEnum.FABIRC.getValue().equals(res.getAppType().toLowerCase())){
                validMspDir(config);
            }
            appInfo = res;
        }
    }

    private void valid(Config config) {
        Preconditions.checkNotNull(config.getApi(), "Node gateway Url cannot be null");
        Preconditions.checkNotNull(config.getUserCode(), "Usercode cannot be null");
        Preconditions.checkNotNull(config.getAppCode(), "Appcode cannot be null");
        Preconditions.checkNotNull(config.getPrk(), "User private key cannot be null");
        Preconditions.checkNotNull(config.getPuk(), "User public key cannot be null");
    }

    private void validMspDir(Config config) {
        Preconditions.checkNotNull(config.getMspDir(), "Certificate storage directory cannot be null");
    }

}
