package com.bsnbase.sdk.entity.transactionHeader;

import lombok.Data;

@Data
public class TransactionUser {
    String mspId;
    byte[] cert;
    String privateKey;
}
