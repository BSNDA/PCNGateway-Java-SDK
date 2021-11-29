package com.bsnbase.sdk.entity.resp.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of chaincode event registration interface
 */

@Data
public class ResChainCodeRegister implements IBody {
    /**
     * Event ID
     */
    String eventId;

    @Override
    public String getEncryptionValue() {
        return (this.eventId == null ? "" : this.eventId);
    }
}
