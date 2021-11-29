package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of block information interface
 */

@Data
public class ReqBlockRegister implements IBody {
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
        return (this.notifyUrl == null ? "" : this.notifyUrl) + (this.attachArgs == null ? "" : this.attachArgs);
    }
}
