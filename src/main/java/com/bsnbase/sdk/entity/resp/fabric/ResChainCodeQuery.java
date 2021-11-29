package com.bsnbase.sdk.entity.resp.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of query chaincode event interface
 */

@Data
public class ResChainCodeQuery implements IBody {
    /**
     * Event ID
     */
    String eventId;
    /**
     * Parameter: Chaincode event key
     * Note: Null when it is the block event
     */
    String eventKey;
    /**
     * Chaincode event notification Url
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
    /**
     * City node code
     */
    String orgCode;
    /**
     * Usercode
     */
    String userCode;
    /**
     * Appcode
     */
    String appCode;
    /**
     * Chaincode
     * Null when it is the block event
     */
    String chainCode;
    /**
     * Event type
     * Chaincode event is null
     */
    String eventType;

    @Override
    public String getEncryptionValue() {
        return (this.eventId == null ? "" : this.eventId)
                + (this.eventKey == null ? "" : this.eventKey)
                + (this.notifyUrl == null ? "" : this.notifyUrl)
                + (this.attachArgs == null ? "" : this.attachArgs)
                + (this.createTime == null ? "" : this.createTime)
                + (this.orgCode == null ? "" : this.orgCode)
                + (this.userCode == null ? "" : this.userCode)
                + (this.appCode == null ? "" : this.appCode)
                + (this.chainCode == null ? "" : this.chainCode)
                + (this.eventType == null ? "" : this.eventType);
    }

}
