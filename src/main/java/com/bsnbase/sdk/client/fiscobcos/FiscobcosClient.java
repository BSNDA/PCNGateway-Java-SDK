package com.bsnbase.sdk.client.fiscobcos;

import com.bsnbase.sdk.client.fiscobcos.service.ChainCodeService;
import com.bsnbase.sdk.client.fiscobcos.service.NodeService;
import com.bsnbase.sdk.client.fiscobcos.service.UserService;
import com.bsnbase.sdk.entity.req.fiscobcos.*;
import com.bsnbase.sdk.entity.res.fiscobcos.*;

import java.io.IOException;
import java.util.List;

public class FiscobcosClient {

    /**
     * 注册用户
     */
    public static ResUserRegister register( ReqUserRegister reqUserRegister) throws Exception {
        ResUserRegister resUserRegister = UserService.userRegister(reqUserRegister);
        return resUserRegister;

    }

    /**
     * 密钥托管模式下调用智能合约接口
     */
    public static ResKeyEscrow reqChainCode(ReqKeyEscrow reqKeyEscrow) throws IOException {
        ResKeyEscrow resKeyEscrow = NodeService.reqChainCode(reqKeyEscrow);
        return resKeyEscrow;
    }

    /**
     * 获取交易回执接口
     */
    public  static ResGetTxReceiptByTxHash getTxReceiptByTxHash(ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash) throws Exception{
        ResGetTxReceiptByTxHash txReceiptByTxHash = NodeService.getTxReceiptByTxHash(reqGetTxReceiptByTxHash);
        return txReceiptByTxHash;
    }


    /**
     * 获取交易信息接口
     */
    public static ResGetTransaction getTxInfoByTxHash(ReqGetTransaction reqGetTxReceiptByTxHash) throws Exception {
        ResGetTransaction txInfoByTxHash = NodeService.getTxInfoByTxHash(reqGetTxReceiptByTxHash);
        return txInfoByTxHash;
    }

    /**
     * 获取块信息接口
     *
     */
    public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqGetBlockInformation){
        ResGetBlockInformation blockInfo = NodeService.getBlockInfo(reqGetBlockInformation);
        return blockInfo;
    }

    /**
     * 获取应用内的块高接口
     *
     */
    public static ResGetBlockHeight getBlockHeight(){
        ResGetBlockHeight blockHeight = NodeService.getBlockHeight();
        return blockHeight;
    }

    /**
     * 获取应用内的交易总数接口
     *
     */
    public static ResGetTxCount getTxCount() throws IOException{
        ResGetTxCount txCount = NodeService.getTxCount();
        return txCount;
    }

    /**
     * 获取块内的交易总数接口
     *
     * @throws IOException
     */
    public static ResGetTxCountByBlockNumber getTxCountByBlockNumber(ReqGetTxCountByBlockNumber reqGetTxCountByBlockNumber) throws IOException{
        ResGetTxCountByBlockNumber txCountByBlockNumber = NodeService.getTxCountByBlockNumber(reqGetTxCountByBlockNumber);
        return txCountByBlockNumber;
    }

    /**
     * 链码事件注册接口
     *
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqChainCodeRegister) {
        ResChainCodeRegister resChainCodeRegister = ChainCodeService.eventRegister(reqChainCodeRegister);
        return resChainCodeRegister;
    }

    /**
     * 链码事件查询接口
     */
    public static List<ResChainCodeQuery> eventQuery() throws Exception {
        List<ResChainCodeQuery> resChainCodeQueries = ChainCodeService.eventQuery();
        return resChainCodeQueries;
    }


    /**
     *
     * 链码事件取消接口
     */
    public static ResChainCodeCancel eventRemove( ReqChainCodeCancel reqChainCodeCancel) throws Exception {
        ResChainCodeCancel resChainCodeCancel = ChainCodeService.eventRemove(reqChainCodeCancel);
        return  resChainCodeCancel;
    }


    /**
     *
     * 公钥上传模式下调用智能合约接口
     */
    public static ResTrans trans(ReqTransData reqTransData) throws Exception {
        ResTrans trans = NodeService.trans(reqTransData);
        return trans;
    }

}