package com.bsnbase.sdk.client.cita;


import com.bsnbase.sdk.client.cita.service.ChainCodeService;
import com.bsnbase.sdk.client.cita.service.NodeService;
import com.bsnbase.sdk.client.cita.service.TransactionService;
import com.bsnbase.sdk.client.cita.service.UserService;
import com.bsnbase.sdk.entity.req.cita.*;
import com.bsnbase.sdk.entity.resp.cita.*;

import java.util.List;

public class CitaClient {

    /**
     * User registration
     * After a user participates in a CITA application service successfully, he/she needs to call this interface through the off-chain business system to generate the user account and account address for on-chain transaction processing.
     */
    public static ResUserRegister userRegister(ReqUserRegister register) throws Exception {
        ResUserRegister resUserRegister = UserService.userRegister(register);
        return resUserRegister;
    }

    /**
     * Invoke the smart contract in Key Trust Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * Example of default contract method call
     * Save data/Update data.
     * FuncName: insert/update
     * FuncParam: The first element is a byte32 string (key), the second element is a byte16 string (value), which can be obtained by Common.getByte32() and Common.getByte16().
     * Delete data/Get data:.
     * FuncName: remove/retrieve
     * FuncParam: the first element is a byte32 string (key)
     */
    public static ResKeyEscrow reqChainCode(ReqKeyEscrow reqkey) throws Exception {
        ResKeyEscrow resKeyEscrow = TransactionService.reqChainCode(reqkey);
        return resKeyEscrow;
    }

    /**
     * Invoke the smart contract in Public Key Upload Mode
     * When the off-chain business system connects to the BSN gateway, it needs to add the corresponding parameters in the request message according to the interface description. After invoking the gateway, the gateway will return the execution result of the smart contract.
     * In the transaction of public key upload mode, the private key of the on-chain transaction is generated and saved by the user, and then the client will assemble and sign the on-chain data locally, and upload the signed data to the node gateway.
     * The gateway forwards the data to the corresponding blockchain node to initiate the transaction request. In this mode, the ABI of the contract and the contract address are required to assemble the data.
     * The ABI of the contract is obtained by compiling the contract when developing the contract, and the contract address can be obtained from the details page of the participated service.
     * The method to assemble the on-chain data has been implemented in the SDK of the gateway, and it can be called directly.
     * -----------------------------------------------------------------------------------------------------------------
     * Example of default contract method parameters
     * FuncParam: the parameter type of the preset chaincode package is already handled in Service, just pass String.
     * <p>
     * Save data
     * FuncName:insert
     * FuncParam:{"insert1219", "123456"}
     * <p>
     * Remove data
     * FuncName:remove
     * FuncParam:{"insert1219"}
     * <p>
     * Update data
     * FuncName:update
     * FuncParam:{"insert1219", "1234567"}
     * Retrieve data
     * FuncName:retrieve
     * FuncParam:{"insert1219"}
     * <p>
     * Get key by index
     * FuncName:keyAtIndex
     * FuncParam:{"1"}
     * -----------------------------------------------------------------------------------------------------------------
     */
    public static ResKeyEscrow nodeTrans(ReqKeyUpload reqKey) throws Exception {
        return TransactionService.nodeTrans(reqKey);
    }

    /**
     * Get Transaction Receipt
     * After a smart contract executes a transaction, you can use this interface to get the transaction's receipt information based on the transaction's hash value.
     */
    public static ResGetTxReceiptByTxHash getTxReceiptByTxHash(ReqGetTxReceiptByTxHash reqGetTxReceiptByTxHash) throws Exception {
        ResGetTxReceiptByTxHash txReceiptByTxHash = NodeService.getTxReceiptByTxHash(reqGetTxReceiptByTxHash);
        return txReceiptByTxHash;
    }


    /**
     * Get Transaction Information
     * After a smart contract executes a transaction, you can use this interface to get the transaction's detailed information based on the transaction's hash value.
     */
    public static ResGetTransaction getTransaction(ReqGetTransaction reqData) {
        ResGetTransaction transaction = NodeService.getTransaction(reqData);
        return transaction;
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
     * Get the block height
     * Get the block height based on this interface.
     */
    public static ResGetBlockHeight getBlockHeight() {
        ResGetBlockHeight resGetBlockHeight = NodeService.getBlockHeight();
        return resGetBlockHeight;
    }

    /**
     * Chaincode event registration
     * You can register cita's block event or contract event through this interface, and when the event is triggered, the system will send the event content to the registered notification address.
     */
    public static ResChainCodeRegister eventRegister(ReqChainCodeRegister reqData) {
        ResChainCodeRegister resChainCodeRegister = ChainCodeService.eventRegister(reqData);
        return resChainCodeRegister;
    }


    /**
     * Query chaincode event
     * This interface can query the list of registered events
     */
    public static List<ResChainCodeQuery> eventQuery() {
        List<ResChainCodeQuery> resChainCodeQueryList = ChainCodeService.eventQuery();
        return resChainCodeQueryList;
    }


    /**
     * Remove chaincode event
     * This interface can remove the registered events.
     */
    public static ResChainCodeCancel eventRemove(ReqChainCodeCancel reqChainCodeCancel) {
        ResChainCodeCancel resChainCodeCancel = ChainCodeService.eventRemove(reqChainCodeCancel);
        return resChainCodeCancel;
    }
}