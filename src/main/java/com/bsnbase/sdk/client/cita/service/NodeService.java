package com.bsnbase.sdk.client.cita.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.ReqGetBlockInformation;
import com.bsnbase.sdk.entity.req.cita.ReqGetTransaction;
import com.bsnbase.sdk.entity.req.cita.ReqGetTxReceiptByTxHash;
import com.bsnbase.sdk.entity.resp.cita.ResGetBlockHeight;
import com.bsnbase.sdk.entity.resp.cita.ResGetBlockInformation;
import com.bsnbase.sdk.entity.resp.cita.ResGetTransaction;
import com.bsnbase.sdk.entity.resp.cita.ResGetTxReceiptByTxHash;
import com.bsnbase.sdk.util.path.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;

public class NodeService {

    /**
     * Get Transaction Receipt
     * After a smart contract executes a transaction, you can use this interface to get the transaction's receipt information based on the transaction's hash value.
     */
    public static ResGetTxReceiptByTxHash getTxReceiptByTxHash(ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash) {
        String api = Config.config.getApi() + PathUtil.CITA_NODE_GET_TXRECEIPT_BY_TXHASH;
        BaseReqModel<ReqGetTxReceiptByTxHash> req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(reqGetTxReceiptByTxHash);
        HttpService<ReqGetTxReceiptByTxHash, ResGetTxReceiptByTxHash> httpService = new HttpService();
        BaseResModel<ResGetTxReceiptByTxHash> res = httpService.post(req, api, ResGetTxReceiptByTxHash.class);
        return res.getBody();
    }

    /**
     * Get Transaction Information
     * After a smart contract executes a transaction, you can use this interface to get the transaction's detailed information based on the transaction's hash value.
     */
    public static ResGetTransaction getTransaction(ReqGetTransaction reqData) {
        String api = Config.config.getApi() + PathUtil.CITA_NODE_GET_TXINFO_BY_TXHASH;
        BaseReqModel<ReqGetTransaction> req = new BaseReqModel<ReqGetTransaction>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqGetTransaction, ResGetTransaction> httpService = new HttpService<ReqGetTransaction, ResGetTransaction>();
        BaseResModel<ResGetTransaction> res = httpService.post(req, api, ResGetTransaction.class);

        return res.getBody();
    }

    /**
     * Get block information
     * When the data is uploaded and a block is generated, the information of the responding block can be queried according to the block number or the block hash, where the block number and the block hash cannot be empty at the same time.
     * When both are not empty, the information of the block corresponding to the block number is queried in priority.
     */
    public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqData) {
        String api = Config.config.getApi() + PathUtil.CITA_NODE_GET_BLOCK_INFO;
        BaseReqModel<ReqGetBlockInformation> req = new BaseReqModel<ReqGetBlockInformation>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqGetBlockInformation, ResGetBlockInformation> httpService = new HttpService<ReqGetBlockInformation, ResGetBlockInformation>();
        BaseResModel<ResGetBlockInformation> res = httpService.post(req, api, ResGetBlockInformation.class);
        return res.getBody();
    }


    /**
     * Get the block height
     * Get the block height based on this interface.
     */
    public static ResGetBlockHeight getBlockHeight() {
        String api = Config.config.getApi() + PathUtil.CITA_NODE_GET_BLOCK_HEIGHT;
        BaseReqModel req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService httpService = new HttpService<>();
        BaseResModel<ResGetBlockHeight> res = httpService.post(req, api, ResGetBlockHeight.class);
        return res.getBody();
    }


}
