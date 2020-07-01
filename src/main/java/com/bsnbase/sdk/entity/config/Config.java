package com.bsnbase.sdk.entity.config;

import java.io.IOException;

import com.bsnbase.sdk.client.fabric.service.AppService;
import com.bsnbase.sdk.entity.res.ResUserInfo;
import com.bsnbase.sdk.util.exception.GlobalException;
import com.bsnbase.sdk.util.keystore.IKeyStore;
import com.bsnbase.sdk.util.keystore.KeyStore;
import lombok.Data;

@Data
public class Config {
    /**
     *  PCN gateway address
     */
    String api;
    /**
     *user number
     */
    String userCode;
    /**
     *user code
     */
    String appCode;
    /**
     *DApp code
     */
    String puk;
    /**
     *Private key
     */
    String prk;
    /**
     *cert storage directory
     */
    String mspDir;
    /**
     *cert
     */
    String cert;

    //APPinfo
    ResUserInfo appInfo;

    //subuser cert storage processing 
    IKeyStore keyStore;

    public static Config config;

    public void initConfig(Config cg){
        if (config == null) {
            config = cg;
            keyStore = new KeyStore(config.getMspDir());
            ResUserInfo res = null;
            try {
                res = AppService.getAppInfo();
            } catch (IOException e) {
                e.printStackTrace();
                throw new GlobalException("failed access to App info");
            }
            appInfo = res;

        }
    }
}
