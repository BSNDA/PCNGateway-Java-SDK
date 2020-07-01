package com.bsnbase.sdk.entity.res;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResGetBlockTranaction {
    String txId;
    int status;
    String createName;
    long timeSpanSec;
    long timeSpanNsec;
}
