package com.bsnbase.sdk.entity.transactionHeader;

import lombok.Data;

import java.util.Map;

/**
 * Assemble the request parameter of transaction information
 */

@Data
public class TransactionRequest {
    /**
     * Parameter: Name of the application chain
     * Note: In Fabric it is in the form of channelId, and in fisco it is in the form of groupId
     */
    String channelId;
    /**
     * Chaincode
     */
    String chaincodeId;
    /**
     * Method name
     */
    String fcn;
    /**
     * Request parameters
     */
    byte[][] args;
    /**
     * Transient data
     */
    Map<String, String> transientMap;

}
