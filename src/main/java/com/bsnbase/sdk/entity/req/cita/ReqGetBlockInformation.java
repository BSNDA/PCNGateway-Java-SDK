package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get block information interface
 */
@Data
public class ReqGetBlockInformation implements IBody {
    /**
     * Block number
     */
    String blockNumber;
    /**
     * Block hash
     */
    String blockHash;

    @Override
    public String getEncryptionValue() {
        return (this.blockNumber == null ? "" : this.blockNumber) + (this.blockHash == null ? "" : this.blockHash);
    }
}
