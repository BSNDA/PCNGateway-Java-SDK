package com.bsnbase.sdk.entity.transactionHeader;

import lombok.Data;

/**
 * Assemble the user parameters of transaction information
 */
@Data
public class TransactionUser {
    /**
     * User information MSPID
     */
    String mspId;
    /**
     * Certificate information
     */
    byte[] cert;
    /**
     * Private key
     */
    String privateKey;

    boolean isSM3;
}
