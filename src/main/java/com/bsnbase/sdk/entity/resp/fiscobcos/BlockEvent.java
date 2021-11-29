package com.bsnbase.sdk.entity.resp.fiscobcos;

import lombok.Data;

/**
 * Response parameters of query chaincode event interface
 */

@Data
public class BlockEvent {
    /**
     * Block event
     */
    String eventId;
    /**
     * Appcode
     */
    String appcode;
    /**
     * Usercode
     */
    String userCode;
    /**
     * Notification Url
     */
    String notifyUrl;
    /**
     * Attached args
     */
    String attachArgs;
    /**
     * Created time
     * UTC time
     */
    String createTime;
}
