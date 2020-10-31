package com.bsnbase.sdk.entity.res.cita;

import lombok.Data;

@Data
public class Transactions {
    String txHash;
    String data;
    String chainId;
    String quota;
    String from;
    String to;
    String nonce;
    String validUntilBlock;
    String version;
}
