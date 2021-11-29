package com.bsnbase.sdk.entity.resp.cita;

import lombok.Data;

/**
 * Response parameters of get block information interface
 */
@Data
public class Transactions {
    /**
     * Transaction hash
     */
    String txHash;
    /**
     * Transaction data
     */
    String data;
    /**
     * Chaincode ID
     */
    String chainId;
    /**
     * Transaction quota
     */
    String quota;
    /**
     * Transaction sender
     */
    String from;
    /**
     * Transaction receiver
     */
    String to;
    /**
     * Nonce
     */
    String nonce;
    /**
     * The maximum block height for on-chain transactions
     */
    String validUntilBlock;
    /**
     * version
     */
    String version;
}
