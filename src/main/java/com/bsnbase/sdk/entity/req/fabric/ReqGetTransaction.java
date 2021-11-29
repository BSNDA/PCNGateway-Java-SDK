package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get transaction information interface
 */

@Data
public class ReqGetTransaction implements IBody {
    /**
     * Transaction ID
     */
    String txId;

    @Override
    public String getEncryptionValue() {
        return (this.txId == null ? "" : this.txId);
    }
}
