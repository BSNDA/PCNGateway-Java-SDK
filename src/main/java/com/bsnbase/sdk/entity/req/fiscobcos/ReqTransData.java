package com.bsnbase.sdk.entity.req.fiscobcos;

import lombok.Data;

import java.util.List;

/**
 * Request parameters for FISCO-BCOS calling the smart contract interface in Public Key Upload Mode
 */
@Data
public class ReqTransData {
    /**
     * Contract ABI
     * Note: When the user permission is no-check permission and the called contract method is constant, the contract at that address will be called and the contract ABI of the contractAbi field will be used to parse the transaction data
     */
    String contractAbi;
    /**
     * Contract address
     * Note: When the user permission is no-check permission and the called contract method is constant, the contract at that address will be called and the contract ABI of the contractAbi field will be used to parse the transaction data
     */
    String contractAddress;
    /**
     * Contract name
     */
    String ContractName;
    /**
     * Method name
     */
    String funcName;
    /**
     * Method parameters
     */
    List<Object> funcParam;
    /**
     * Username
     */
    String userName;
}
