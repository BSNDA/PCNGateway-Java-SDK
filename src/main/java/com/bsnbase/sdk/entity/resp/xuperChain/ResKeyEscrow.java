package com.bsnbase.sdk.entity.resp.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of invoke the smart contract in Key Trust Mode interface
 */
@Data
public class ResKeyEscrow implements IBody {
    /**
     * Transaction ID
     * Note: Invoke contract, cannot be null
     */
    String txId;
    /**
     * Query information
     * Note: Query contract, cannot be null
     */
    String queryInfo;


    @Override
    public String getEncryptionValue() {
        String str = (this.txId == null ? "" : this.txId) + (this.queryInfo == null ? "" : this.queryInfo);
        return str;

    }
}
