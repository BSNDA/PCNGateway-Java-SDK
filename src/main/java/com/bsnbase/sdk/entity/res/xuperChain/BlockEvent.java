package com.bsnbase.sdk.entity.res.xuperChain;

import lombok.Data;

@Data
public class BlockEvent {
    String eventId;
    String eventKey;
    String userCode;
    String appCode;
    String contractName;
    String notifyUrl;
    String attachArgs;
    String createTime;
}
