package com.bsnbase.sdk.entity.resp.cita;

import lombok.Data;

/**
 * Response parameters of get transaction receipt interface
 */

@Data
public class Log {
    /**
     * Whether to remove
     */
    Boolean removed;
    /**
     * Log index
     */
    Long logIndex;
    /**
     * Transaction index
     */
    Long transactionIndex;
    /**
     * Transaction hash
     */
    String transactionHash;
    /**
     * Block hash
     */
    String blockHash;
    /**
     * Block number
     */
    Long blockNumber;
    /**
     * Contract address
     */
    String address;
    /**
     * Log filtered by index array
     */
    String data;
    /**
     * Transaction event index
     */
    String transactionLogIndex;
    /**
     * Transaction index
     */
    String transactionIndexRaw;
    /**
     * Block number
     */
    String blockNumberRaw;
    /**
     * Log index
     */
    String logIndexRaw;
    /**
     * The index array used to construct the filter
     */
    String[] topics;
}
