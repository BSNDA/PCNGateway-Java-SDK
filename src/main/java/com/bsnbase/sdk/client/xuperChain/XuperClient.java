package com.bsnbase.sdk.client.xuperChain;

import com.bsnbase.sdk.client.xuperChain.service.NodeService;
import com.bsnbase.sdk.client.xuperChain.service.UserService;
import com.bsnbase.sdk.entity.req.xuperChain.ReqGetBlockInformation;
import com.bsnbase.sdk.entity.req.xuperChain.ReqGetTransaction;
import com.bsnbase.sdk.entity.req.xuperChain.ReqKeyEscrow;
import com.bsnbase.sdk.entity.req.xuperChain.ReqUserRegister;
import com.bsnbase.sdk.entity.res.xuperChain.ResGetBlockInformation;
import com.bsnbase.sdk.entity.res.xuperChain.ResGetTransaction;
import com.bsnbase.sdk.entity.res.xuperChain.ResKeyEscrow;
import com.bsnbase.sdk.entity.res.xuperChain.ResUserRegister;

import java.io.IOException;


public class XuperClient {


    /**
     * 注册用户
     */
    public static ResUserRegister register(ReqUserRegister reqUserRegister) {
        ResUserRegister resUserRegister= UserService.userRegister(reqUserRegister);
        return resUserRegister;
    }


    /**
     * 密钥托管模式下调用智能合约接口
     */
    public static ResKeyEscrow reqChainCode(ReqKeyEscrow reqKeyEscrow) throws IOException {
            ResKeyEscrow resKeyEscrow= NodeService.reqChainCode(reqKeyEscrow);
            return resKeyEscrow;
    }

    /**
     * 获取交易信息接口
     */
    public static ResGetTransaction getTxInfoByTxHash(ReqGetTransaction reqGetTransaction) {
            ResGetTransaction resGetTransaction= NodeService.getTxInfoByTxHash(reqGetTransaction);
            return resGetTransaction;
    }


    /**
     * 获取块信息接口
     */
    public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqGetBlockInformation) {
        ResGetBlockInformation resGetBlockInformation = NodeService.getBlockInfo(reqGetBlockInformation);
        return resGetBlockInformation;
    }
}