package com.bsnbase.sdk.entity.config;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.bsnbase.sdk.client.fabric.service.AppService;
import com.bsnbase.sdk.entity.res.fabric.ResUserInfo;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import com.bsnbase.sdk.util.keystore.IKeyStore;
import com.bsnbase.sdk.util.keystore.KeyStore;

import lombok.Data;

@Data
public class Config {
    /**
     * 节点网关地址
     */
    String api;
    /**
     * 用户编号
     */
    String userCode;
    /**
     * 用户编号
     */
    String appCode;
    /**
     * 应用公钥
     */
    String puk;
    /**
     * 应用私钥
     */
    String prk;
    /**
     * 证书存数目录
     */
    String mspDir;
    /**
     * 证书
     */
    @Deprecated
    String cert;
    /**
     * 网关公钥配置标识
     * true  ：测试网服务
     * false ：正常服务
     */
    boolean testServerIdn;

    //APP信息
    ResUserInfo appInfo;

    //子用户证书存储处理
    IKeyStore keyStore;

    public static Config config;

    public void initConfig(Config cg) {
        if (config == null) {
            config = cg;
            keyStore = new KeyStore(config.getMspDir());
            ResUserInfo res = null;
            try {
                res = AppService.getAppInfo();
            } catch (IOException e) {
                e.printStackTrace();
                throw new GlobalException(ResultInfoEnum.GET_APP_INFO_ERROR);
            }
            appInfo = res;
        }
    }

    /**
     * 读取本地配置文件
     * @param filePath
     * @return
     */
    public static Config buildLocalConfigByJson(String filePath) {
        String result="";
        try {
             result= Common.readLocalFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.CONFIG_NOT_EXISTS);
        }
        Config config=buildConfgiByJsonStr(result);
        return config;
    };

    /**
     * 读取项目配置resource目录下文件
     * @param filePath
     * @return
     */
    public static Config buildByConfigJson(String filePath) {
        String result="";
        try {
            result= Common.readFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException(ResultInfoEnum.CONFIG_NOT_EXISTS);
        }
        Config config=buildConfgiByJsonStr(result);

        return config;
    }

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

}
