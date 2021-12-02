package com.bsnbase.sdk.client.fabric.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fabric.*;
import com.bsnbase.sdk.entity.resp.fabric.*;
import com.bsnbase.sdk.util.path.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;

public class NodeService {

    /**
     * Get transaction information
     * The off-chain system can use this interface to get the transaction's detailed information based on the transaction ID.
     */
    public static ResGetTransaction getTransaction(ReqGetTransaction reqData) {
        String api = Config.config.getApi() + PathUtil.FABRIC_NODE_GET_TRANSACTION;
        BaseReqModel<ReqGetTransaction> req = new BaseReqModel<ReqGetTransaction>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqGetTransaction, ResGetTransaction> httpService = new HttpService<ReqGetTransaction, ResGetTransaction>();
        BaseResModel<ResGetTransaction> res = httpService.post(req, api, ResGetTransaction.class);
        return res.getBody();
    }

    /**
     * Get block information
     * After the data is uploaded, the off-chain business system will call the node gateway to get the block information (body.blockInfo), status value (body.blockInfo.status) and transaction ID (body.blockInfo.txId) of the current transaction.
     * If the status value is 0, it means the transaction is submitted successfully and the block is generated, and the user can query the block information based on the transaction ID.
     */
    public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqData) {
        String api = Config.config.getApi() + PathUtil.FABRIC_NODE_GET_BLOCKINFO;
        BaseReqModel<ReqGetBlockInformation> req = new BaseReqModel<ReqGetBlockInformation>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(reqData);
        HttpService<ReqGetBlockInformation, ResGetBlockInformation> httpService = new HttpService<ReqGetBlockInformation, ResGetBlockInformation>();
        BaseResModel<ResGetBlockInformation> res = httpService.post(req, api, ResGetBlockInformation.class);
        return res.getBody();
    }

    /**
     * Get the latest ledger information
     * Get the latest ledger information of the application, including block hash, previous block hash, the current block height, and other information.
     */
    public static ResGetLedgerInfo getLedgerInfo() {
        String api = Config.config.getApi() + PathUtil.FABRIC_NODE_GET_LEDGER_INFO;
        BaseReqModel<ReqGetLedgerInfo> req = new BaseReqModel<ReqGetLedgerInfo>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqGetLedgerInfo, ResGetLedgerInfo> httpService = new HttpService<ReqGetLedgerInfo, ResGetLedgerInfo>();
        BaseResModel<ResGetLedgerInfo> res = httpService.post(req, api, ResGetLedgerInfo.class);
        return res.getBody();
    }

    /**
     * Get transaction data
     * This interface can be used by the off-chain system to obtain transaction data based on the transaction ID, and the transaction information is returned in the form of base64 string.
     */
    public static ResTransData getTransData(ReqTransData reqTransData) {
        String api = Config.config.getApi() + PathUtil.FABRIC_NODE_GET_TRANS_DATA;
        BaseReqModel<ReqTransData> reqData = new BaseReqModel<>();
        reqData.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        reqData.setBody(reqTransData);
        HttpService<ReqTransData, ResTransData> httpService = new HttpService<>();
        BaseResModel<ResTransData> res = httpService.post(reqData, api, ResTransData.class);
        return res.getBody();
    }

    /**
     * Get the block data
     * After the data is uploaded, the off-chain business system calls this interface through the node gateway to get the block information of the current transaction
     */
    public static ResGetBlockData getBlockData(ReqGetBlockData reqGetBlockData) {
        String api = Config.config.getApi() + PathUtil.FABRIC_NODE_GET_BLOCK_DATA;
        BaseReqModel<ReqGetBlockData> req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(reqGetBlockData);
        HttpService<ReqGetBlockData, ResGetBlockData> httpService = new HttpService<>();
        BaseResModel<ResGetBlockData> res = httpService.post(req, api, ResGetBlockData.class);
        return res.getBody();
    }


}
