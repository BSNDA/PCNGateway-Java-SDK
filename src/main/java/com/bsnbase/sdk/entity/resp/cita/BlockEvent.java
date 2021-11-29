package com.bsnbase.sdk.entity.resp.cita;

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
     */
    String createTime;


}