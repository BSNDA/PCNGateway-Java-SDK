package com.bsnbase.sdk.client.xuperChain;

import com.bsnbase.sdk.client.xuperChain.service.ChainCodeService;
import com.bsnbase.sdk.client.xuperChain.service.NodeService;
import com.bsnbase.sdk.client.xuperChain.service.UserService;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.*;
import com.bsnbase.sdk.entity.res.xuperChain.*;
import com.bsnbase.sdk.util.sm2.Sm2SignUtil;
import com.bsnbase.sdk.util.trans.XuperTransUtil;
import com.bsnbase.sdk.util.xuper.Account;
import com.bsnbase.sdk.util.xuper.Base58;
import com.bsnbase.sdk.util.xuper.Hash;
import com.bsnbase.sdk.util.xuper.SM3;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.math.ec.ECPoint;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;


public class XuperClient {


    /**
     * 注册用户
     */
    public static ResUserRegister register(ReqUserRegister reqUserRegister) {
        ResUserRegister resUserRegister = UserService.userRegister(reqUserRegister);
        return resUserRegister;
    }

    /**
     * 公钥上传模式下调用智能合约Query接口
     */
    public static ResKeyEscrowNo reqChainCode(ReqTransData reqTransData) throws IOException {
        ReqTrans reqTrans = XuperTransUtil.buildRequest(reqTransData);
        ResKeyEscrowNo resKeyEscrowNo = NodeService.nodeTrans(reqTrans);
        return resKeyEscrowNo;
    }

    /**
     * 公钥上传模式下调用智能合约invoke接口
     * @param reqTransData
     * @return
     * @throws Exception
     */
    public static ResKeyEscrowNo reqInvokeChainCode(ReqTransData reqTransData) throws Exception {
        ReqTrans reqTrans = XuperTransUtil.buildRequest(reqTransData);
        ResKeyEscrowNo resKeyEscrowNo = NodeService.nodeTrans(reqTrans);
        ReqTrans invokeReqTrans = XuperTransUtil.buildTransaction(reqTransData, resKeyEscrowNo);
        ResKeyEscrowNo resInvokeKeyEscrowNo = NodeService.nodeTrans(invokeReqTrans);
        return resInvokeKeyEscrowNo;
    }


    /**
     * 密钥托管模式下调用智能合约接口
     */
    public static ResKeyEscrow reqChainCode(ReqKeyEscrow reqKeyEscrow) throws IOException {
        ResKeyEscrow resKeyEscrow = NodeService.reqChainCode(reqKeyEscrow);
        return resKeyEscrow;
    }


    /**
     * 获取交易信息接口
     */
    public static ResGetTransaction getTxInfoByTxHash(ReqGetTransaction reqGetTransaction) {
        ResGetTransaction resGetTransaction = NodeService.getTxInfoByTxHash(reqGetTransaction);
        return resGetTransaction;
    }


    /**
     * 获取块信息接口
     */
    public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqGetBlockInformation) {
        ResGetBlockInformation resGetBlockInformation = NodeService.getBlockInfo(reqGetBlockInformation);
        return resGetBlockInformation;
    }

    /**
     * 链码事件注册接口
     */
    public static ResChainCodeRegister reqChainCodeRegister(ReqChainCodeRegister reqChainCodeRegister) {
        ResChainCodeRegister resChainCodeRegister = ChainCodeService.eventRegister(reqChainCodeRegister);
        return resChainCodeRegister;
    }

    /**
     * 链码事件查询接口
     */
    public static List<ResChainCodeQuery> reqChainCodeQuery() {
        List<ResChainCodeQuery> resChainCodeQuery = ChainCodeService.eventQuery();
        return resChainCodeQuery;
    }

    /**
     * 链码事件注销接口
     */
    public static ResChainCodeCancel reqChainCodeCancel(ReqChainCodeCancel reqChainCodeCancel) {
        ResChainCodeCancel resChainCodeCancel = ChainCodeService.eventRemove(reqChainCodeCancel);
        return resChainCodeCancel;
    }

    /**
     * 获取xuperchain
     * @return
     * @throws Exception
     */
    public static String getUserAddress() throws Exception {
        PublicKey publicKey= Sm2SignUtil.getPublicKey(Config.config.getPuk().getBytes());
        ECPublicKey ecPublicKey= (ECPublicKey) publicKey;
        ECPoint ecPoint=ecPublicKey.getQ();
        byte[] pubkey = ecPoint.getEncoded(false);
        byte[] hash = Hash.ripeMD128(SM3.hash(pubkey));
        byte[] strSlice=new byte[]{2};
        strSlice= Account.byteMerger(strSlice,hash);
        byte[] checkCode = SM3.hash(strSlice);
        byte[] simpleCheckCode=Account.subBytes(checkCode,0,4);
        byte[] slice=Account.byteMerger(strSlice,simpleCheckCode);
        String address = Base58.encode( slice);
        return address;
    }

}