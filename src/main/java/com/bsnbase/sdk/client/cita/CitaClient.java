package com.bsnbase.sdk.client.cita;


import com.bsnbase.sdk.client.cita.service.ChainCodeService;
import com.bsnbase.sdk.client.cita.service.NodeService;
import com.bsnbase.sdk.client.cita.service.TransactionService;
import com.bsnbase.sdk.client.cita.service.UserService;
import com.bsnbase.sdk.entity.req.cita.*;
import com.bsnbase.sdk.entity.res.cita.*;

import java.util.List;

public class CitaClient {

    /**
     * 用户注册
     */
    public static ResUserRegister userRegister(ReqUserRegister register) throws Exception {
        ResUserRegister resUserRegister = UserService.userRegister(register);
        return resUserRegister;
    }

    /**
     * 密钥托管模式交易处理
     */
    public static ResKeyEscrow reqChainCode(ReqKeyEscrow reqkey) throws Exception {
        ResKeyEscrow resKeyEscrow = TransactionService.reqChainCode(reqkey);
        return resKeyEscrow;
    }

    /**
     * 公钥上传模式交易处理
     */
    public static ResKeyEscrow nodeTrans(ReqKeyUpload reqKey) throws Exception {
        return TransactionService.nodeTrans(reqKey);
    }

    /**
     * 获取交易回执接口
     */
    public static ResGetTxReceiptByTxHash getTxReceiptByTxHash(ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash) throws Exception {
        ResGetTxReceiptByTxHash txReceiptByTxHash = NodeService.getTxReceiptByTxHash(reqGetTxReceiptByTxHash);
        return txReceiptByTxHash;
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
        ResGetBlockInformation resGetBlockInformation = NodeService.getBlockInfo(reqGetBlockInformation);
        return resGetBlockInformation;
    }

    /**
     * 获取应用内的块高接口
     */
    public static ResGetBlockHeight getBlockHeight() {
        ResGetBlockHeight resGetBlockHeight = NodeService.getBlockHeight();
        return resGetBlockHeight;
    }
    /**
     * 链码事件注册
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqData) {
        ResChainCodeRegister resChainCodeRegister= ChainCodeService.eventRegister(reqData);
        return resChainCodeRegister;
    }



    /**
     * 链码事件查询
     */
    public static List<ResChainCodeQuery> eventQuery() {
        List<ResChainCodeQuery> resChainCodeQueryList= ChainCodeService.eventQuery();
        return resChainCodeQueryList;
    }


    /**
     * 链码事件取消接口
     */
    public static ResChainCodeCancel eventRemove(ReqChainCodeCancel reqChainCodeCancel) {
        ResChainCodeCancel resChainCodeCancel= ChainCodeService.eventRemove(reqChainCodeCancel);
        return resChainCodeCancel;
    }
}