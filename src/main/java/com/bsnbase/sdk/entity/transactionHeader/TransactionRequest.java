package com.bsnbase.sdk.entity.transactionHeader;

import lombok.Data;

import java.util.Map;

@Data
public class TransactionRequest {
    String chanelId;
    String chaincodeId;
    String fcn;
    byte[][] args;
    Map<String,String> transientMap;

}
