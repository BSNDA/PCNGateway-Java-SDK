package com.bsnbase.sdk.entity.resp.fabric;

import lombok.Data;

@Data
public class ResGetBlockTranaction {
    /**
     * Transaction ID
     */
    String txId;
    /**
     * Transaction status
     * Note: See transaction status descriptions
     */
    int status;
    /**
     * Transaction committer
     */
    String createName;
    /**
     * Transaction timestamp by second
     */
    long timeSpanSec;
    /**
     * Transaction timestamp by nanosecond
     */
    long timeSpanNsec;
}
