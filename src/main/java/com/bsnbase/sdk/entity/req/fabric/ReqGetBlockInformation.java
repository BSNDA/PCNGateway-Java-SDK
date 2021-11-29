package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get block information interface
 */

@Data
public class ReqGetBlockInformation implements IBody {
    /**
     * block number
     * parameters cannot both be null
     */
    Integer blockNumber;
    /**
     * block hash
     * parameters cannot both be null
     */
    String blockHash;
    /**
     * Transaction ID
     * parameters cannot both be null
     */
    String txId;

    @Override
    public String getEncryptionValue() {
        return (this.blockNumber == null ? "0" : this.blockNumber) + (this.blockHash == null ? "" : this.blockHash) + (this.txId == null ? "" : this.txId);
    }
}
