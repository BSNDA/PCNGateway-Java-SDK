package com.bsnbase.sdk.client.fabric.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResArrayModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fabric.ReqBlockRegister;
import com.bsnbase.sdk.entity.req.fabric.ReqChainCodeQuery;
import com.bsnbase.sdk.entity.req.fabric.ReqChainCodeRegister;
import com.bsnbase.sdk.entity.req.fabric.ReqChainCodeRemove;
import com.bsnbase.sdk.entity.resp.fabric.ResBlockRegister;
import com.bsnbase.sdk.entity.resp.fabric.ResChainCodeQuery;
import com.bsnbase.sdk.entity.resp.fabric.ResChainCodeRegister;
import com.bsnbase.sdk.entity.resp.fabric.ResChainCodeRemove;
import com.bsnbase.sdk.util.PathUtil.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;

import java.util.List;

public class ChainCodeService {


    /**
     * Chaincode event registration
     * If the user who has participated in the application needs to trigger the off-chain business system for subsequent business processing by the chaincode event, he/she can call this interface to register the chaincode event to be listened to.
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqData) {
        String api = Config.config.getApi() + PathUtil.FABRIC_CHAIN_CODE_REGISTER;
        BaseReqModel<ReqChainCodeRegister> req = new BaseReqModel<ReqChainCodeRegister>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqChainCodeRegister, ResChainCodeRegister> httpService = new HttpService<ReqChainCodeRegister, ResChainCodeRegister>();
        BaseResModel<ResChainCodeRegister> res = httpService.post(req, api, ResChainCodeRegister.class);
        return res.getBody();
    }

    /**
     * Query chaincode event
     * This interface can query the list of registered events.
     */
    public static List<ResChainCodeQuery> eventQuery() {
        String api = Config.config.getApi() + PathUtil.FABRIC_CHAIN_CODE_EVENT_QUERY;
        BaseReqModel<ReqChainCodeQuery> req = new BaseReqModel<ReqChainCodeQuery>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqChainCodeQuery, ResChainCodeQuery> httpService = new HttpService<ReqChainCodeQuery, ResChainCodeQuery>();
        BaseResArrayModel<ResChainCodeQuery> res = httpService.arrayPost(req, api, ResChainCodeQuery.class);
        return res.getBody();
    }

    /**
     * Remove chaincode event
     * This interface can remove the registered events.
     */
    public static ResChainCodeRemove eventRemove(ReqChainCodeRemove reqData) {
        String api = Config.config.getApi() + PathUtil.FABRIC_CHAIN_CODE_EVENT_REMOVE;
        BaseReqModel<ReqChainCodeRemove> req = new BaseReqModel<ReqChainCodeRemove>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqChainCodeRemove, ResChainCodeRemove> httpService = new HttpService<ReqChainCodeRemove, ResChainCodeRemove>();
        BaseResModel<ResChainCodeRemove> res = httpService.post(req, api, ResChainCodeRemove.class);
        return res.getBody();
    }


    /**
     * Block event registration
     * If the user who has participated in the application needs to trigger the off-chain business system for subsequent business processing by the block event, he/she can call this interface to register the block event to be listened to.
     */
    public static ResBlockRegister eventBlockRegister(ReqBlockRegister reqData) {
        String api = Config.config.getApi() + PathUtil.FABRIC_CHAIN_CODE_EVENT_BLOCK_REGISTER;
        BaseReqModel<ReqBlockRegister> req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqBlockRegister, ResBlockRegister> httpService = new HttpService<>();
        BaseResModel<ResBlockRegister> res = httpService.post(req, api, ResBlockRegister.class);
        return res.getBody();
    }


}
