package com.bsnbase.sdk.entity.resp.xuperChain;

import lombok.Data;

/**
 * Response parameters of query chaincode event interface
 */
@Data
public class BlockEvent {
    /**
     * Event ID
     */
    String eventId;
    /**
     * Chaincode event key
     * Is null for block event
     */
    String eventKey;
    /**
     * Usercode
     */
    String userCode;
    /**
     * Appcode
     */
    String appCode;
    /**
     * Contract name
     */
    String contractName;
    /**
     * 通Notification Url
     */
    String notifyUrl;
    /**
     * Attached args
     */
    String attachArgs;
    /**
     * Created time
     * Note: UTC time
     */
    String createTime;
}
