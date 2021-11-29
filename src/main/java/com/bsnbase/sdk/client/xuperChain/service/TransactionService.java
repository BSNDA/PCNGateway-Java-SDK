package com.bsnbase.sdk.client.xuperChain.service;

import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.xuperChain.ReqTrans;
import com.bsnbase.sdk.entity.resp.xuperChain.ResKeyEscrow;
import com.bsnbase.sdk.entity.resp.xuperChain.ResKeyEscrowNo;
import com.bsnbase.sdk.util.PathUtil.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TransactionService {
    /**
     * Invoke the smart contract interface in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description.After invoking the gateway, the gateway will return the execution result of the smart contract.
     * -----------------------------------------------------------------------------------------------------------
     * Example of default contract method parameters:
     * Save data:
     * FuncName: insert_data
     * FuncParam:{"base_key":"dev_0001","base_value":"test"}
     * Remove data:
     * FuncName: remove_data
     * FuncParam:{"base_key":"dev_0001"}
     * Update data:
     * FuncName: update_data
     * FuncParam:{"base_key":"dev_0001","base_value":"test2"}
     * Retrieve data:
     * FuncName: select_data
     * FuncParam:{"base_key":"dev_0001"}
     * -----------------------------------------------------------------------------------------------------------
     */
    public static ResKeyEscrow reqChainCode(@NotNull ReqKeyEscrow kes) throws IOException {
        String api = Config.config.getApi() + PathUtil.XUPERCHAIN_NODE_REQ_CHAIN_CODE;
        BaseReqModel<ReqKeyEscrow> req = new BaseReqModel<ReqKeyEscrow>(kes);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqKeyEscrow, ResKeyEscrow> httpService = new HttpService<ReqKeyEscrow, ResKeyEscrow>();
        BaseResModel<ResKeyEscrow> res = httpService.post(req, api, ResKeyEscrow.class);
        return res.getBody();

    }


    /**
     * Invoke the smart contract interface in Public Key Upload Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * In the transaction of public key upload mode, the private key of the on-chain transaction is generated and saved by the user, and then the client will assemble and sign the on-chain data locally, and upload the signed data to the node gateway.
     * The method to assemble the on-chain data has been implemented in the SDK of the gateway, and it can be called directly.
     */
    public static ResKeyEscrowNo nodeTrans(@NotNull ReqTrans kes) throws IOException {
        String api = Config.config.getApi() + PathUtil.XUPERCHAIN_NODE_TRANS;
        BaseReqModel<ReqTrans> req = new BaseReqModel<>(kes);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqTrans, ResKeyEscrowNo> httpService = new HttpService<>();
        BaseResModel<ResKeyEscrowNo> res = httpService.post(req, api, ResKeyEscrowNo.class);
        return res.getBody();

    }
}
