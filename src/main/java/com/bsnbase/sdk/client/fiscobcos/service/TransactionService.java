package com.bsnbase.sdk.client.fiscobcos.service;


import com.bsnbase.sdk.entity.base.BaseReqModel;
import com.bsnbase.sdk.entity.base.BaseResModel;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqTrans;
import com.bsnbase.sdk.entity.req.fiscobcos.ReqTransData;
import com.bsnbase.sdk.entity.resp.fiscobcos.ResKeyEscrow;
import com.bsnbase.sdk.entity.resp.fiscobcos.ResTrans;
import com.bsnbase.sdk.util.PathUtil.PathUtil;
import com.bsnbase.sdk.util.common.HttpService;
import com.bsnbase.sdk.util.trans.FiscoTransUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TransactionService {

    /**
     * Invoke the smart contract interface in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description.
     * After invoking the gateway, the gateway will return the execution result of the smart contract.
     *
     * @param kes
     * @return
     * @throws IOException
     */
    public static ResKeyEscrow reqChainCode(@NotNull ReqKeyEscrow kes) throws IOException {
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_NODE_REQ_CHAINCODE;
        BaseReqModel<ReqKeyEscrow> req = new BaseReqModel<ReqKeyEscrow>(kes);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        System.out.println(kes.getEncryptionValue());
        req.setBody(kes);
        HttpService<ReqKeyEscrow, ResKeyEscrow> httpService = new HttpService<ReqKeyEscrow, ResKeyEscrow>();
        BaseResModel<ResKeyEscrow> res = httpService.post(req, api, ResKeyEscrow.class);
        return res.getBody();

    }


    /**
     * Invoke the smart contract in Public Key Upload Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * In the transaction of public key upload mode, the private key of the on-chain transaction is generated and saved by the user, and then the client will assemble and sign the on-chain data locally, and upload the signed data to the node gateway.
     * The gateway forwards the data to the corresponding blockchain node to initiate the transaction request. In this mode, the ABI of the contract and the contract address are required to assemble the data.
     * The ABI of the contract is obtained by compiling the contract when developing the contract, and the contract address can be obtained from the details page of the participated service.
     * The method to assemble the on-chain data has been implemented in the SDK of the gateway, and it can be called directly.
     */
    public static ResTrans trans(ReqTransData reqTransData) throws Exception {
        ReqTrans reqTrans = FiscoTransUtil.buildTrans(reqTransData);
        String api = Config.config.getApi() + PathUtil.FISCOBCOS_NODE_TRANS;
        BaseReqModel<ReqTrans> req = new BaseReqModel<>(reqTrans);
        req.setReqHeader(Config.config.getUserCode(), Config.config.getAppCode());
        HttpService<ReqTrans, ResTrans> httpService = new HttpService();
        BaseResModel<ResTrans> res = httpService.post(req, api, ResTrans.class);
        return res.getBody();
    }


}
