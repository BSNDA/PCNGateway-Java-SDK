package com.bsnbase.sdk.client.fabric;


import com.bsnbase.sdk.client.fabric.service.ChainCodeService;
import com.bsnbase.sdk.client.fabric.service.NodeService;
import com.bsnbase.sdk.client.fabric.service.TransactionService;
import com.bsnbase.sdk.client.fabric.service.UserService;
import com.bsnbase.sdk.entity.req.fabric.*;
import com.bsnbase.sdk.entity.resp.fabric.*;

import java.util.List;

public class FabricClient {


    /**
     * User registration
     * <p>
     * In both key trust mode and public key upload mode, when the user is participating in the Fabric application and needs to create a separate user transaction certificate for the sub-user of the off-chain system, it is necessary to call this interface first to register the user in this city node, and the user's username is name@appCode in the call parameter.
     */
    public static ResUserRegister userRegister(ReqUserRegister register) throws Exception {
        ResUserRegister resUserRegister = UserService.userRegister(register);
        return resUserRegister;
    }

    /**
     * Invoke chaincode in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description.
     * After invoking the gateway, the gateway will return the execution result of the chaincode.
     * This interface will directly response the result and does not wait for the transaction to block. User can call "Retrive Transaction Information API" to query the block based on the transaction ID.；
     */
    public static ResKeyEscrow reqChainCode(ReqKeyEscrow reqkey) throws Exception {
        ResKeyEscrow resKeyEscrow = TransactionService.reqChainCode(reqkey);
        return resKeyEscrow;
    }

    /**
     * User certificate registration in Public Key Upload Mode
     * When a user participated in the application in the public key upload mode needs to register a sub-user, after completing the user registration interface, he/she can call this interface to upload a public key certificate application file and obtain a sub-user certificate issued by the city node.
     * An exception will be returned when this interface is called in key trust mode.
     */
    public static ResKeyEscrowEnroll userEnroll(ReqKeyEscrowEnroll reqKeyEscrowEnroll) throws Exception {
        ResKeyEscrowEnroll resKeyEscrowEnroll = UserService.userEnroll(reqKeyEscrowEnroll);
        return resKeyEscrowEnroll;
    }

    /**
     * Invoke chaincode in Public Key Upload Mode
     * <p>
     * When the user of the public key upload mode application needs to initiate a transaction from the off-chain system to the chaincode on the chain, he/she needs to assemble the transaction message locally and call this interface to initiate the transaction.
     */
    public static ResKeyEscrowNo nodeTrans(ReqKeyEscrow reqkey) throws Exception {
        ResKeyEscrowNo resKeyEscrowNo = TransactionService.nodeTrans(reqkey);
        return resKeyEscrowNo;
    }


    /**
     * Get Transaction Information
     * The off-chain system can use this interface to get the transaction's detailed information based on the transaction ID.
     */
    public static ResGetTransaction getTransaction(ReqGetTransaction reqData) {
        ResGetTransaction transaction = NodeService.getTransaction(reqData);
        return transaction;
    }

    /**
     * Get block information
     * After the data is uploaded, the off-chain business system will call the node gateway to get the block information (body.blockInfo), status value (body.blockInfo.status) and transaction ID (body.blockInfo.txId) of the current transaction.
     * If the status value is 0, it means the transaction is submitted successfully and the block is generated, and the user can query the block information based on the transaction ID.
     */
    public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqGetBlockInformation) {
        ResGetBlockInformation resGetBlockInformation = NodeService.getBlockInfo(reqGetBlockInformation);
        return resGetBlockInformation;
    }

    /**
     * Get the latest ledger information
     * Get the latest ledger information of the application, including block hash, previous block hash, the current block height, and other information.
     */
    public static ResGetLedgerInfo getLedgerInfo() {
        ResGetLedgerInfo ledgerInfo = NodeService.getLedgerInfo();
        return ledgerInfo;
    }

    /**
     * Chaincode event registration
     * If the user who has participated in the application needs to trigger the off-chain business system for subsequent business processing by the chaincode event, he/she can call this interface to register the chaincode event to be listened to.
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqChainCodeRegister) {
        ResChainCodeRegister resChainCodeRegister = ChainCodeService.eventRegister(reqChainCodeRegister);
        return resChainCodeRegister;
    }

    /**
     * Query chaincode event
     * This interface can query the list of registered events.
     */
    public static List<ResChainCodeQuery> eventQuery() {
        List<ResChainCodeQuery> resChainCodeQueries = ChainCodeService.eventQuery();
        return resChainCodeQueries;
    }

    /**
     * Remove chaincode event
     * This interface can remove the registered events.
     */
    public static ResChainCodeRemove eventRemove(ReqChainCodeRemove reqChainCodeRemove) {
        ResChainCodeRemove resChainCodeRemove = ChainCodeService.eventRemove(reqChainCodeRemove);
        return resChainCodeRemove;
    }

    /**
     * Block event registration
     * If the user who has participated in the application needs to trigger the off-chain business system for subsequent business processing by the block event, he/she can call this interface to register the block event to be listened to.
     */
    public static ResBlockRegister eventBlockRegister(ReqBlockRegister reqBlockRegister) {
        ResBlockRegister resChainCodeRegister = ChainCodeService.eventBlockRegister(reqBlockRegister);
        return resChainCodeRegister;
    }

    /**
     * Get transaction data
     * This interface can be used by the off-chain system to obtain transaction data based on the transaction ID, and the transaction information is returned in the form of base64 string.
     */
    public static ResTransData getTransData(ReqTransData reqTransData) {
        ResTransData resTransData = NodeService.getTransData(reqTransData);
        return resTransData;
    }

    /**
     * Get block data
     * After the data is uploaded, the off-chain business system calls this interface through the node gateway to get the block information of the current transaction
     */
    public static ResGetBlockData getBlockData(ReqGetBlockData reqGetBlockData) {
        ResGetBlockData resGetBlockData = NodeService.getBlockData(reqGetBlockData);
        return resGetBlockData;
    }
}