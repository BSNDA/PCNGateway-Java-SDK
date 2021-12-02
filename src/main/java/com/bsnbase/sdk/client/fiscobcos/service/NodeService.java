package com.bsnbase.sdk.client.fiscobcos.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.*;
import com.bsnbase.sdk.entity.resp.fiscobcos.*;
import com.bsnbase.sdk.util.path.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class NodeService {

    /**
     * Get transaction information
     * After a smart contract executes a transaction, you can use this interface to get the transaction's detailed information based on the transaction's hash value.
     */
    public static ResGetTransaction getTxInfoByTxHash(ReqGetTransaction reqData) {
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_NODE_GET_TXINFO_BY_TXHASH;
        BaseReqModel<ReqGetTransaction> req = new BaseReqModel<ReqGetTransaction>(reqData);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
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
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_NODE_GET_BLOCK_INFO;
        BaseReqModel<ReqGetBlockInformation> req = new BaseReqModel<ReqGetBlockInformation>(reqData);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqGetBlockInformation, ResGetBlockInformation> httpService = new HttpService<ReqGetBlockInformation, ResGetBlockInformation>();
        BaseResModel<ResGetBlockInformation> res = httpService.post(req, api, ResGetBlockInformation.class);
        return res.getBody();
    }

    /**
     * Get the block height
     * Get the block height based on this interface.
     */
    public static ResGetBlockHeight getBlockHeight() {
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_NODE_GET_BLOCK_HEIGHT;
        BaseReqModel req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService httpService = new HttpService<>();
        BaseResModel<ResGetBlockHeight> res = httpService.post(req, api, ResGetBlockHeight.class);
        return res.getBody();
    }

    /**
     * Get the total number of transactions
     * The total number of transactions can be Getd by calling this interface
     */
    public static ResGetTxCount getTxCount() throws IOException {
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_NODE_GET_TXCOUNT;
        BaseReqModel<ReqKeyEscrow> req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService httpService = new HttpService();
        BaseResModel<ResGetTxCount> res = httpService.post(req, api, ResGetTxCount.class);
        return res.getBody();

    }

    /**
     * Get the total number of transactions in the block
     * The total number of transactions in a block can be queried within the FISCO-BCOS application based on the block number, where the block number cannot be empty.
     */
    public static ResGetTxCountByBlockNumber getTxCountByBlockNumber(@NotNull ReqGetTxCountByBlockNumber reqGetTxCountByBlockNumber) throws IOException {
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_NODE_GET_TXCOUNT_BY_BLOCK_NUMBER;
        BaseReqModel<ReqGetTxCountByBlockNumber> req = new BaseReqModel<>(reqGetTxCountByBlockNumber);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqGetTxCountByBlockNumber, ResGetTxCountByBlockNumber> httpService = new HttpService();
        BaseResModel<ResGetTxCountByBlockNumber> res = httpService.post(req, api, ResGetTxCountByBlockNumber.class);
        return res.getBody();
    }

    /**
     * Get transaction receipt
     * After a smart contract executes a transaction, you can use this interface to get the transaction's receipt information based on the transaction's hash value.
     */
    public static ResGetTxReceiptByTxHash getTxReceiptByTxHash(ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash) throws NoSuchAlgorithmException {
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_NODE_GET_TXRECEIPT_BY_TXHASH;
        BaseReqModel<ReqGetTxReceiptByTxHash> req = new BaseReqModel<>();
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        req.setBody(reqGetTxReceiptByTxHash);
        HttpService<ReqGetTxReceiptByTxHash, ResGetTxReceiptByTxHash> httpService = new HttpService();
        BaseResModel<ResGetTxReceiptByTxHash> res = httpService.post(req, api, ResGetTxReceiptByTxHash.class);
        return res.getBody();
    }

}
