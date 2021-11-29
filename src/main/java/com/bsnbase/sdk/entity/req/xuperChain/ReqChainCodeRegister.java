package com.bsnbase.sdk.entity.req.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;


/**
 * Request parameters of chaincode event registration interface
 */
@Data
public class ReqChainCodeRegister implements IBody {
    /**
     * Contract name
     */
    String contractName;
    /**
     * Contract event key
     */
    String eventKey;
    /**
     * Event notification Url
     * Note: the address used to receive the listened event
     */
    String notifyUrl;
    /**
     * Attached args
     */
    String attachArgs;

    @Override
    public String getEncryptionValue() {
        return (this.contractName == null ? "" : this.contractName)
                + (this.eventKey == null ? "" : this.eventKey)
                + (this.notifyUrl == null ? "" : this.notifyUrl)
                + (this.attachArgs == null ? "" : this.attachArgs);
    }
}
