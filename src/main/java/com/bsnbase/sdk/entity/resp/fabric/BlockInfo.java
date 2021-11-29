package com.bsnbase.sdk.entity.resp.fabric;

import lombok.Data;

/**
 * Response parameters of invoke the smart contract interface
 */

@Data
public class BlockInfo {
    /**
     * Transaction ID
     */
    String txId;
    /**
     * Block hash
     * Note: Synchronous interface returns block hash
     */
    String blockHash;
    /**
     * Status value
     * Note: See transaction status description for details
     */
    Integer status;

}
