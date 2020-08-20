package com.bsnbase.sdk.client.fabric.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fabric.ReqUserInfo;
import com.bsnbase.sdk.entity.res.fabric.ResUserInfo;
import com.bsnbase.sdk.util.common.HttpService;

import java.io.IOException;

public class AppService {

    /**
     * 获取应用信息
     * @return
     * @throws IOException
     */
    public static ResUserInfo getAppInfo() throws IOException{
        String api =  Config.config.getApi() + "/api/app/getAppInfo";
        BaseReqModel<ReqUserInfo> req = new  BaseReqModel<ReqUserInfo>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());

        HttpService<ReqUserInfo,ResUserInfo> httpService =new HttpService<ReqUserInfo,ResUserInfo>();
        BaseResModel<ResUserInfo> res = httpService.noSignPost(req,api, ResUserInfo.class );

        return res.getBody();
    }

}
