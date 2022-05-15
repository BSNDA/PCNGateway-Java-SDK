package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get block data interface
 */

@Data
public class ReqGetBlockData implements IBody {
    /**
     * Block number
     */
    Integer blockNumber;
    /**
     * Block hash
     */
    String blockHash;
    /**
     * Transaction ID
     */
    String txId;

    /**
     * DataType Options as json
     */
    String dataType ;

    @Override
    public String getEncryptionValue() {
        return (this.blockNumber == null ? 0 : this.blockNumber)
                + (this.blockHash == null ? "" : this.blockHash)
                + (this.txId == null ? "" : this.txId)
                + (this.dataType == null ? "" : this.dataType);
    }
}
