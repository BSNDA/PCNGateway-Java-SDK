package com.bsnbase.sdk.entity.resp.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of get the latest ledger information interface
 */

@Data
public class ResGetLedgerInfo implements IBody {
    /**
     * Block hash
     */
    String blockHash;
    /**
     * Block height
     */
    Long height;
    /**
     * Previous block hash
     */
    String preBlockHash;

    @Override
    public String getEncryptionValue() {
        return (this.blockHash == null ? "" : this.blockHash)
                + (this.height == null ? "" : this.height)
                + (this.preBlockHash == null ? "" : this.preBlockHash);
    }
}
