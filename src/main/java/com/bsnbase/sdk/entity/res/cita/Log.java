package com.bsnbase.sdk.entity.res.cita;

import com.alibaba.fastjson.JSON;
import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class Log {
    Boolean removed;
    Long logIndex;
    Long transactionIndex;
    String transactionHash;
    String blockHash;
    Long blockNumber;
    String address;
    String data;
    String transactionLogIndex;
    String transactionIndexRaw;
    String blockNumberRaw;
    String logIndexRaw;
    String[] topics;
}
