package com.bsnbase.sdk.entity.resp.fiscobcos;

import lombok.Data;

/**
 * Response parameters of get block information interface
 */
@Data
public class ResGetBlockTranaction {
    /**
     * Transaction hash
     */
    String txId;
    /**
     * Block hash
     */
    String blockHash;
    /**
     * Block number
     */
    int blockNumber;
    /**
     * Gas used
     */
    int gasUsed;
    /**
     * Transaction sender
     */
    String from;
    /**
     * Transaction receiver
     */
    String to;
    /**
     * Transferred value
     */
    int value;
    /**
     * Input of the transaction
     */
    String input;
}
