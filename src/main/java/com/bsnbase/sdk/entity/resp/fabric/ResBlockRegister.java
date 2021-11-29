package com.bsnbase.sdk.entity.resp.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Data;

/**
 * Response parameters of block information registration interface
 */

@Data
public class ResBlockRegister implements IBody {
    /**
     * Event ID
     */
    String eventId;

    @Override
    public String getEncryptionValue() {
        return (this.eventId == null ? "" : this.eventId);
    }
}
