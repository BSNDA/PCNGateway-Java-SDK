package com.bsnbase.sdk.client.xuperChain;

import com.bsnbase.sdk.client.xuperChain.service.ChainCodeService;
import com.bsnbase.sdk.client.xuperChain.service.NodeService;
import com.bsnbase.sdk.client.xuperChain.service.TransactionService;
import com.bsnbase.sdk.client.xuperChain.service.UserService;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.xuperChain.*;
import com.bsnbase.sdk.entity.resp.xuperChain.*;
import com.bsnbase.sdk.util.sign.Sm2SignUtil;
import com.bsnbase.sdk.util.trans.XuperTransUtil;
import com.bsnbase.sdk.util.xuper.Account;
import com.bsnbase.sdk.util.xuper.Base58;
import com.bsnbase.sdk.util.xuper.Hash;
import com.bsnbase.sdk.util.xuper.SM3;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.math.ec.ECPoint;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;


public class XuperClient {


    /**
     * User registration
     * After a user participates in a XuperChain-based application, he/she needs to call this interface through the off-chain business system to generate the user account and account address for on-chain transaction processing.
     */
    public static ResUserRegister register(ReqUserRegister reqUserRegister) {
        ResUserRegister resUserRegister = UserService.userRegister(reqUserRegister);
        return resUserRegister;
    }

    /**
     * Query the smart contract interface in Public Key Upload Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * In the transaction of public key upload mode, the private key of the on-chain transaction is generated and saved by the user, and then the client will assemble and sign the on-chain data locally, and upload the signed data to the node gateway.
     * The method to assemble the on-chain data has been implemented in the SDK of the gateway, and it can be called directly.
     */
    public static ResKeyEscrowNo reqChainCode(ReqTransData reqTransData) throws IOException {
        ReqTrans reqTrans = XuperTransUtil.buildRequest(reqTransData);
        ResKeyEscrowNo resKeyEscrowNo = TransactionService.nodeTrans(reqTrans);
        return resKeyEscrowNo;
    }

    /**
     * Invoke the smart contract in Public Key Upload Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * In the transaction of public key upload mode, the private key of the on-chain transaction is generated and saved by the user, and then the client will assemble and sign the on-chain data locally, and upload the signed data to the node gateway.
     * The method to assemble the on-chain data has been implemented in the SDK of the gateway, and it can be called directly.
     */
    public static ResKeyEscrowNo reqInvokeChainCode(ReqTransData reqTransData) throws Exception {
        ReqTrans reqTrans = XuperTransUtil.buildRequest(reqTransData);
        ResKeyEscrowNo resKeyEscrowNo = TransactionService.nodeTrans(reqTrans);
        ReqTrans invokeReqTrans = XuperTransUtil.buildTransaction(reqTransData, resKeyEscrowNo);
        ResKeyEscrowNo resInvokeKeyEscrowNo = TransactionService.nodeTrans(invokeReqTrans);
        return resInvokeKeyEscrowNo;
    }


    /**
     * Invoke the smart contract in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
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
    public static ResKeyEscrow reqChainCode(ReqKeyEscrow reqKeyEscrow) throws IOException {
        ResKeyEscrow resKeyEscrow = TransactionService.reqChainCode(reqKeyEscrow);
        return resKeyEscrow;
    }


    /**
     * Get transaction information
     * After a smart contract executes a transaction, you can use this interface to get the transaction's detailed information based on the transaction's hash value.
     */
    public static ResGetTransaction getTxInfoByTxHash(ReqGetTransaction reqGetTransaction) {
        ResGetTransaction resGetTransaction = NodeService.getTxInfoByTxHash(reqGetTransaction);
        return resGetTransaction;
    }


    /**
     * Get block information
     * When the data is uploaded and a block is generated, the information of the responding block can be queried according to the block number or the block hash, where the block number and the block hash cannot be empty at the same time.
     * When both are not empty, the information of the block corresponding to the block number is queried in priority.
     */
    public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqGetBlockInformation) {
        ResGetBlockInformation resGetBlockInformation = NodeService.getBlockInfo(reqGetBlockInformation);
        return resGetBlockInformation;
    }

    /**
     * Chaincode event registration
     * If the user who has participated in the application needs to trigger the off-chain business system for subsequent business processing by the chaincode event, he/she can call this interface to register the chaincode event to be listened to.
     */
    public static ResChainCodeRegister reqChainCodeRegister(ReqChainCodeRegister reqChainCodeRegister) {
        ResChainCodeRegister resChainCodeRegister = ChainCodeService.eventRegister(reqChainCodeRegister);
        return resChainCodeRegister;
    }

    /**
     * Query chaincode event
     * This interface can query the list of registered events.
     */
    public static List<ResChainCodeQuery> reqChainCodeQuery() {
        List<ResChainCodeQuery> resChainCodeQuery = ChainCodeService.eventQuery();
        return resChainCodeQuery;
    }

    /**
     * Remove chaincode event
     * This interface can remove the registered events.
     */
    public static ResChainCodeCancel reqChainCodeCancel(ReqChainCodeCancel reqChainCodeCancel) {
        ResChainCodeCancel resChainCodeCancel = ChainCodeService.eventRemove(reqChainCodeCancel);
        return resChainCodeCancel;
    }

    /**
     * Retrieve account address in Public Key Upload Mode
     */
    public static String getUserAddress() throws Exception {
        PublicKey publicKey = Sm2SignUtil.getPublicKey(Config.config.getPuk().getBytes());
        ECPublicKey ecPublicKey = (ECPublicKey) publicKey;
        ECPoint ecPoint = ecPublicKey.getQ();
        byte[] pubkey = ecPoint.getEncoded(false);
        byte[] hash = Hash.ripeMD128(SM3.hash(pubkey));
        byte[] strSlice = new byte[]{2};
        strSlice = Account.byteMerger(strSlice, hash);
        byte[] checkCode = SM3.hash(strSlice);
        byte[] simpleCheckCode = Account.subBytes(checkCode, 0, 4);
        byte[] slice = Account.byteMerger(strSlice, simpleCheckCode);
        String address = Base58.encode(slice);
        return address;
    }

}