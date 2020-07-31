package com.bsnbase.sdk.entity.res.fiscobcos;

import lombok.Data;

@Data
public class BlockEvent {
    String eventId;
    String appcode;
    String userCode;
    String notifyUrl;
    String attachArgs;
    String createTime;
}
