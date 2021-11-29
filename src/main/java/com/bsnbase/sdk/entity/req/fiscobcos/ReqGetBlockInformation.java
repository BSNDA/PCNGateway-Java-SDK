package com.bsnbase.sdk.entity.req.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of FISCO-BCOS get block information interface
 */
@Data
public class ReqGetBlockInformation implements IBody {
    /**
     * Block number
     * Cannot be null when the blockHash is null
     */
    String blockNumber;
    /**
     * Block hash
     * Cannot be null when the blockNumber is null
     */
    String blockHash;

    @Override
    public String getEncryptionValue() {
        return (this.blockNumber == null ? "" : this.blockNumber) + (this.blockHash == null ? "" : this.blockHash);
    }
}
