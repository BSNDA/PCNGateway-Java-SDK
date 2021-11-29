package com.bsnbase.sdk.entity.resp.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of invoke the smart contract in Public Key Upload Mode interface
 */
@Data
public class ResKeyEscrowNo implements IBody {
    /**
     * Transaction ID
     */
    String txId;
    /**
     * Query information
     */
    String queryInfo;
    /**
     * Response message
     */
    String PreExecRes;

    @Override
    public String getEncryptionValue() {
        return (this.txId == null ? "" : this.txId)
                + (this.queryInfo == null ? "" : this.queryInfo)
                + (this.PreExecRes == null ? "" : this.PreExecRes);
    }
}
