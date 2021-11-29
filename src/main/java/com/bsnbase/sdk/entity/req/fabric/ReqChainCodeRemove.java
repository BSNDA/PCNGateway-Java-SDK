package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of remove chaincode event interface
 */

@Data
public class ReqChainCodeRemove implements IBody {
    /**
     * Event ID
     */
    String eventId;

    @Override
    public String getEncryptionValue() {
        return (this.eventId == null ? "" : this.eventId);
    }
}
