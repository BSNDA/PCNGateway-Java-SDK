package com.bsnbase.sdk.entity.transactionHeader;

import com.google.protobuf.ByteString;
import lombok.Data;

/**
 * Assemble the request header of transaction information
 */
@Data
public class TransactionHeader {
    /**
     * Transaction ID
     */
    String transactionId;
    /**
     * Creator of the message, a marshaled msp.SerializedIdentity
     */
    ByteString creator;
    /**
     * Arbitrary number that may only be used once. Can be used to detect replay attacks.
     */
    ByteString nonce;
    /**
     * Parameter: Name of the application chain
     * Note: In Fabric it is in the form of channelId, and in fisco it is in the form of groupId
     */
    String channelId;

}
