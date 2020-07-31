package com.bsnbase.sdk.entity.res.fiscobcos;

import lombok.Data;

@Data
public class BlockContractEvent {
    String eventId;
    String appcode;
    String userCode;
    String notifyUrl;
    String attachArgs;
    String createTime;
    String contractAddress;
}
