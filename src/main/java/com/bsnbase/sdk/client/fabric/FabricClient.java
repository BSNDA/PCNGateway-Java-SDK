package com.bsnbase.sdk.client.fabric;


import com.bsnbase.sdk.client.fabric.service.ChainCodeService;
import com.bsnbase.sdk.client.fabric.service.NodeService;
import com.bsnbase.sdk.client.fabric.service.TransactionService;
import com.bsnbase.sdk.client.fabric.service.UserService;
import com.bsnbase.sdk.entity.req.fabric.*;
import com.bsnbase.sdk.entity.res.fabric.*;

import java.util.List;

public class FabricClient {

    /**
     * 用户注册
     */
    public static ResUserRegister userRegister( ReqUserRegister register) throws Exception {
        ResUserRegister resUserRegister = UserService.userRegister(register);
        return  resUserRegister;
    }

    /**
     * 密钥托管模式交易处理
     */
    public static ResKeyEscrow reqChainCode(ReqKeyEscrow reqkey) throws Exception {
        ResKeyEscrow resKeyEscrow = TransactionService.reqChainCode(reqkey);
        return resKeyEscrow;
    }

    /**
     * 公钥上传模式用户证书登记
     */
    public static ResKeyEscrowEnroll userEnroll(ReqKeyEscrowEnroll reqKeyEscrowEnroll) throws Exception {
        ResKeyEscrowEnroll resKeyEscrowEnroll = UserService.userEnroll(reqKeyEscrowEnroll);
        return resKeyEscrowEnroll;
    }

    /**
     * 公钥上传模式交易
     */
    public static ResKeyEscrowNo nodeTrans(ReqKeyEscrow reqkey) throws Exception {
            ResKeyEscrowNo resKeyEscrowNo = TransactionService.nodeTrans(reqkey);
            return resKeyEscrowNo;
    }


    /**
     * 获取交易信息
     */
    public static ResGetTransaction getTransaction(ReqGetTransaction reqData) {
        ResGetTransaction transaction = NodeService.getTransaction(reqData);
        return transaction;
    }

    /**
     * 获取块信息
     */
    public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqGetBlockInformation) {
       ResGetBlockInformation resGetBlockInformation=  NodeService.getBlockInfo(reqGetBlockInformation);
       return resGetBlockInformation;
    }

    /**
     * 获取最新账本信息
     */
    public static ResGetLedgerInfo getLedgerInfo() {
        ResGetLedgerInfo ledgerInfo = NodeService.getLedgerInfo();
        return ledgerInfo;
    }

    /**
     * 链码事件注册
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqChainCodeRegister) {
        ResChainCodeRegister resChainCodeRegister = ChainCodeService.eventRegister(reqChainCodeRegister);
        return resChainCodeRegister;
    }

    /**
     * 链码事件查询
     */
    public static  List<ResChainCodeQuery> eventQuery() {
        List<ResChainCodeQuery> resChainCodeQueries = ChainCodeService.eventQuery();
        return resChainCodeQueries;
    }

    /**
     * 链码事件注销
     */
    public static ResChainCodeRemove eventRemove(ReqChainCodeRemove reqChainCodeRemove) {
        ResChainCodeRemove resChainCodeRemove = ChainCodeService.eventRemove(reqChainCodeRemove);
        return resChainCodeRemove;
    }
}