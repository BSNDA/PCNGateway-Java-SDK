package com.bsnbase.sdk.client.fiscobcos;

import com.bsnbase.sdk.client.fiscobcos.service.ChainCodeService;
import com.bsnbase.sdk.client.fiscobcos.service.NodeService;
import com.bsnbase.sdk.client.fiscobcos.service.TransactionService;
import com.bsnbase.sdk.client.fiscobcos.service.UserService;
import com.bsnbase.sdk.entity.req.fiscobcos.*;
import com.bsnbase.sdk.entity.resp.fiscobcos.*;

import java.io.IOException;
import java.util.List;

public class FiscobcosClient {

    /**
     * User registration
     * <p>
     * In both key trust mode and public key upload mode, when the user participated in the FISCO BCOS application needs to create a separate user transaction certificate for the sub-user of the off-chain system, it is necessary to call this interface first to register the sub-user in this city node, and the sub-user's username is name@appCode in the call parameter.
     */
    public static ResUserRegister register(ReqUserRegister reqUserRegister) throws Exception {
        ResUserRegister resUserRegister = UserService.userRegister(reqUserRegister);
        return resUserRegister;

    }

    /**
     * Invoke the smart contract interface in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description.
     * After invoking the gateway, the gateway will return the execution result of the smart contract.
     */
    public static ResKeyEscrow reqChainCode(ReqKeyEscrow reqKeyEscrow) throws IOException {
        ResKeyEscrow resKeyEscrow = TransactionService.reqChainCode(reqKeyEscrow);
        return resKeyEscrow;
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
        ResTrans trans = TransactionService.trans(reqTransData);
        return trans;
    }

    /**
     * Get transaction receipt
     * After a smart contract executes a transaction, you can use this interface to get the transaction's receipt information based on the transaction's hash value.
     */
    public static ResGetTxReceiptByTxHash getTxReceiptByTxHash(ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash) throws Exception {
        ResGetTxReceiptByTxHash txReceiptByTxHash = NodeService.getTxReceiptByTxHash(reqGetTxReceiptByTxHash);
        return txReceiptByTxHash;
    }


    /**
     * Get transaction information
     * After a smart contract executes a transaction, you can use this interface to get the transaction's detailed information based on the transaction's hash value.
     */
    public static ResGetTransaction getTxInfoByTxHash(ReqGetTransaction reqGetTxReceiptByTxHash) throws Exception {
        ResGetTransaction txInfoByTxHash = NodeService.getTxInfoByTxHash(reqGetTxReceiptByTxHash);
        return txInfoByTxHash;
    }

    /**
     * Get block information
     * When the data is uploaded and a block is generated, the information of the responding block can be queried according to the block number or the block hash, where the block number and the block hash cannot be empty at the same time.
     * When both are not empty, the information of the block corresponding to the block number is queried in priority.
     */
    public static ResGetBlockInformation getBlockInfo(ReqGetBlockInformation reqGetBlockInformation) {
        ResGetBlockInformation blockInfo = NodeService.getBlockInfo(reqGetBlockInformation);
        return blockInfo;
    }

    /**
     * Get the block height
     * Get the block height based on this interface.
     */
    public static ResGetBlockHeight getBlockHeight() {
        ResGetBlockHeight blockHeight = NodeService.getBlockHeight();
        return blockHeight;
    }

    /**
     * Get the total number of transactions
     * The total number of transactions can be retrieved by calling this interface
     */
    public static ResGetTxCount getTxCount() throws IOException {
        ResGetTxCount txCount = NodeService.getTxCount();
        return txCount;
    }

    /**
     * Get the total number of transactions in the block
     * The total number of transactions in a block can be queried within the FISCO-BCOS application based on the block number, where the block number cannot be empty.
     */
    public static ResGetTxCountByBlockNumber getTxCountByBlockNumber(ReqGetTxCountByBlockNumber reqGetTxCountByBlockNumber) throws IOException {
        ResGetTxCountByBlockNumber txCountByBlockNumber = NodeService.getTxCountByBlockNumber(reqGetTxCountByBlockNumber);
        return txCountByBlockNumber;
    }

    /**
     * Chaincode event registration
     * You can register FISCO-BCOS's block event or contract event through this interface, and when the event is triggered, the system will send the event content to the registered notification address.
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqChainCodeRegister) {
        ResChainCodeRegister resChainCodeRegister = ChainCodeService.eventRegister(reqChainCodeRegister);
        return resChainCodeRegister;
    }

    /**
     * Query chaincode event
     * This interface can query the list of registered chaincode events and block events.
     */
    public static List<ResChainCodeQuery> eventQuery() throws Exception {
        List<ResChainCodeQuery> resChainCodeQueries = ChainCodeService.eventQuery();
        return resChainCodeQueries;
    }


    /**
     * Remove chaincode event
     * This interface can remove the registered chaincode events and block events.
     */
    public static ResChainCodeCancel eventRemove(ReqChainCodeCancel reqChainCodeCancel) throws Exception {
        ResChainCodeCancel resChainCodeCancel = ChainCodeService.eventRemove(reqChainCodeCancel);
        return resChainCodeCancel;
    }


}