package com.bsnbase.sdk.client.fiscobcos.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.*;
import com.bsnbase.sdk.entity.res.fiscobcos.*;
import com.bsnbase.sdk.util.common.HttpService;
import com.bsnbase.sdk.util.trans.FiscoTransUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class NodeService {

    /**
     * 获取交易信息
     *
     * @param reqData
     */
    public static ResGetTransaction getTxInfoByTxHash(ReqGetTransaction reqData) {
        String api = Config.config.getApi() + "/api/fiscobcos/v1/node/getTxInfoByTxHash";
        BaseReqModel<ReqGetTransaction> req = new BaseReqModel<ReqGetTransaction>(reqData);
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqGetTransaction, ResGetTransaction> httpService = new HttpService<ReqGetTransaction, ResGetTransaction>();
        BaseResModel<ResGetTransaction> res = httpService.post(req, api, Config.config.getCert(), ResGetTransaction.class);

        return res.getBody();
    }

    /**
     * 获取块信息
     *
     * @param reqData
     */
    public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqData) {
        String api = Config.config.getApi() + "/api/fiscobcos/v1/node/getBlockInfo";
        BaseReqModel<ReqGetBlockInformation> req = new BaseReqModel<ReqGetBlockInformation>(reqData);
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqGetBlockInformation, ResGetBlockInformation> httpService = new HttpService<ReqGetBlockInformation, ResGetBlockInformation>();
        BaseResModel<ResGetBlockInformation> res = httpService.post(req, api, Config.config.getCert(), ResGetBlockInformation.class);
        return res.getBody();
    }

    /**
     * 获取应用内的块高接口
     */
    public static ResGetBlockHeight getBlockHeight() {
        String api = Config.config.getApi() + "/api/fiscobcos/v1/node/getBlockHeight";
        BaseReqModel req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService httpService = new HttpService<>();
        BaseResModel<ResGetBlockHeight> res = httpService.post(req, api, Config.config.getCert(), ResGetBlockHeight.class);
        return res.getBody();
    }

    /**
     * 密钥托管模式下调用智能合约接口
     *
     * @param kes
     * @return
     * @throws IOException
     */
    public static ResKeyEscrow reqChainCode(@NotNull ReqKeyEscrow kes) throws IOException {
        String api = Config.config.getApi() + "/api/fiscobcos/v1/node/reqChainCode";
        BaseReqModel<ReqKeyEscrow> req = new BaseReqModel<ReqKeyEscrow>(kes);
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        System.out.println(kes.getEncryptionValue());
        req.setBody(kes);
        HttpService<ReqKeyEscrow, ResKeyEscrow> httpService = new HttpService<ReqKeyEscrow, ResKeyEscrow>();
        BaseResModel<ResKeyEscrow> res = httpService.post(req, api, Config.config.getCert(), ResKeyEscrow.class);
        return res.getBody();

    }


    /**
     * 获取应用内的交易总数
     *
     * @return
     * @throws IOException
     */
    public static ResGetTxCount getTxCount() throws IOException {
        String api = Config.config.getApi() + "/api/fiscobcos/v1/node/getTxCount";
        BaseReqModel<ReqKeyEscrow> req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService httpService = new HttpService();
        BaseResModel<ResGetTxCount> res = httpService.post(req, api, Config.config.getCert(), ResGetTxCount.class);
        return res.getBody();

    }

    /**
     * 获取块内的交易总数接口
     *
     * @return
     * @throws IOException
     */
    public static ResGetTxCountByBlockNumber getTxCountByBlockNumber(@NotNull ReqGetTxCountByBlockNumber reqGetTxCountByBlockNumber) throws IOException {
        String api = Config.config.getApi() + "/api/fiscobcos/v1/node/getTxCountByBlockNumber";
        BaseReqModel<ReqGetTxCountByBlockNumber> req = new BaseReqModel<>(reqGetTxCountByBlockNumber);
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqGetTxCountByBlockNumber, ResGetTxCountByBlockNumber> httpService = new HttpService();
        BaseResModel<ResGetTxCountByBlockNumber> res = httpService.post(req, api, Config.config.getCert(), ResGetTxCountByBlockNumber.class);
        return res.getBody();
    }

    /**
     * 获取交易回执接口
     *
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static ResGetTxReceiptByTxHash getTxReceiptByTxHash(ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash) throws NoSuchAlgorithmException {
        String api = Config.config.getApi() + "/api/fiscobcos/v1/node/getTxReceiptByTxHash";
        BaseReqModel<ReqGetTxReceiptByTxHash> req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        req.setBody(reqGetTxReceiptByTxHash);
        HttpService<ReqGetTxReceiptByTxHash, ResGetTxReceiptByTxHash> httpService = new HttpService();
        BaseResModel<ResGetTxReceiptByTxHash> res = httpService.post(req, api, Config.config.getCert(), ResGetTxReceiptByTxHash.class);
        return res.getBody();
    }


    /**
     * 交易接口
     *
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static ResTrans trans(ReqTransData reqTransData) throws Exception {
        String api = Config.config.getApi() + "/api/fiscobcos/v1/node/trans";
        ReqTrans reqTrans = FiscoTransUtil.buildTrans(reqTransData);
        BaseReqModel<ReqTrans> req = new BaseReqModel<>(reqTrans);
        req.setReqHeader(Config.config.getUserCode(),Config.config.getAppCode());
        HttpService<ReqTrans, ResTrans> httpService = new HttpService();
        BaseResModel<ResTrans> res = httpService.post(req, api, Config.config.getCert(), ResTrans.class);
        return res.getBody();
    }

}
