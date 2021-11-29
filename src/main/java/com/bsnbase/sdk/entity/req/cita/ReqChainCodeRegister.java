package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of chaincode event registration interface
 */
@Data
public class ReqChainCodeRegister implements IBody {
    /**
     * Event type
     * 1：Block event
     * 2：Contract event
     */
    int eventType;
    /**
     * Contract address
     * Can be null when eventType = 1; if eventType = 2, contract address and contract name cannot both be null
     */
    String contractAddress;
    /**
     * Contract name
     * Can be null when eventType = 1; if eventType = 2, contract address and contract name cannot both be null
     */
    String contractName;
    /**
     * Notification Url
     */
    String notifyUrl;
    /**
     * Attached args
     */
    String attachArgs;

    @Override
    public String getEncryptionValue() {
        return (this.eventType == 0 ? "" : this.eventType)
                + (this.contractAddress == null ? "" : this.contractAddress)
                + (this.contractName == null ? "" : this.contractName)
                + (this.notifyUrl == null ? "" : this.notifyUrl)
                + (this.attachArgs == null ? "" : this.attachArgs);
    }
}
