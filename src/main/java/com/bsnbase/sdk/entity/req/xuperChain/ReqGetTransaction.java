package com.bsnbase.sdk.entity.req.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get transaction informaiton interface
 */
@Data
public class ReqGetTransaction implements IBody {
    /**
     * Transaction hash
     */
    String txHash;

    @Override
    public String getEncryptionValue() {
        return (this.txHash == null ? "" : this.txHash);
    }
}
