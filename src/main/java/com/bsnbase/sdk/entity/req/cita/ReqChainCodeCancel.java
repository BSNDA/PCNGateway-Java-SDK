package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of remove chaincode event interface
 */
@Data
public class ReqChainCodeCancel implements IBody {
    /**
     * Event ID
     */
    String eventId;

    @Override
    public String getEncryptionValue() {
        return (this.eventId == null ? "" : this.eventId);
    }
}
