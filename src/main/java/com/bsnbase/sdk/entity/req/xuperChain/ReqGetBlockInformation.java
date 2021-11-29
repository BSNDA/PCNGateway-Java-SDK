package com.bsnbase.sdk.entity.req.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get block informaiton interface
 */
@Data
public class ReqGetBlockInformation implements IBody {
    /**
     * Block height
     * Note: Cannot be null when blockHeight is null
     */
    int blockHeight;
    /**
     * Block hash
     * Note: Cannot be null when blockHeight is null
     */
    String blockHash;

    @Override
    public String getEncryptionValue() {
        return (this.blockHeight + "") + (this.blockHash == null ? "" : this.blockHash);
    }
}
