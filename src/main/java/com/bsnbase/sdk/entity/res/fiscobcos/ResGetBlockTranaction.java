package com.bsnbase.sdk.entity.res.fiscobcos;

import lombok.Data;

@Data
public class ResGetBlockTranaction {
    String txId;
    String blockHash;
    int blockNumber;
    int gasUsed;
    String from;
    String to;
    int value;
    String input;
}
