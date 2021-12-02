package com.bsnbase.sdk.client.xuperChain.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.ReqGetBlockInformation;
import com.bsnbase.sdk.entity.req.xuperChain.ReqGetTransaction;
import com.bsnbase.sdk.entity.resp.xuperChain.ResGetBlockInformation;
import com.bsnbase.sdk.entity.resp.xuperChain.ResGetTransaction;
import com.bsnbase.sdk.util.path.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;

public class NodeService {

    /**
     * Get transaction information
     * After a smart contract executes a transaction, you can use this interface to get the transaction's detailed information based on the transaction's hash value.
     */
    public static ResGetTransaction getTxInfoByTxHash(ReqGetTransaction reqData) {
        String api = Config.config.getApi() + PathUtil.XUPERCHAIN_NODE_GET_TXINFO_BY_TXHASH;
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
        String api = Config.config.getApi() + PathUtil.XUPERCHAIN_NODE_GET_BLOCK_INFO;
        BaseReqModel<ReqGetBlockInformation> req = new BaseReqModel<ReqGetBlockInformation>(reqData);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqGetBlockInformation, ResGetBlockInformation> httpService = new HttpService<ReqGetBlockInformation, ResGetBlockInformation>();
        BaseResModel<ResGetBlockInformation> res = httpService.post(req, api, ResGetBlockInformation.class);
        return res.getBody();
    }

}
