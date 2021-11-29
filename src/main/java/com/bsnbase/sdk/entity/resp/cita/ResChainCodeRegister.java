package com.bsnbase.sdk.entity.resp.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of chaincode event interface
 */
@Data
public class ResChainCodeRegister implements IBody {
    /**
     * Parameter: Event ID
     * Note: Null when code is not 0
     */
    String eventId;

    @Override
    public String getEncryptionValue() {
        return (this.eventId == null ? "" : this.eventId);
    }
}
