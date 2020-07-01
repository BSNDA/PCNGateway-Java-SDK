package com.bsnbase.sdk.entity.transactionHeader;

import com.google.protobuf.ByteString;
import lombok.Data;

@Data
public class TransactionHeader {
    String transactionId;
    ByteString creator;
    ByteString nonce;
    String channelId;

}
