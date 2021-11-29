package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of chaincode event registration interface
 */

@Data
public class ReqChainCodeRegister implements IBody {
    /**
     * Chaincode
     */
    String chainCode;
    /**
     * Chaincode event key
     */
    String eventKey;
    /**
     * Chaincode event notification Url
     * The address to receive the listened chaincode event
     */
    String notifyUrl;
    /**
     * Attached args
     */
    String attachArgs;

    @Override
    public String getEncryptionValue() {
        return (this.chainCode == null ? "" : this.chainCode) + (this.eventKey == null ? "" : this.eventKey) +
                (this.notifyUrl == null ? "" : this.notifyUrl) + (this.attachArgs == null ? "" : this.attachArgs);
    }
}
