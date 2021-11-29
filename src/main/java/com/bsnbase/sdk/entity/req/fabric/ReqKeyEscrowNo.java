package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of invoke chaincode in Public Key Upload Mode interface
 */
@Data
public class ReqKeyEscrowNo implements IBody {
    /**
     * Transaction data
     */
    String transData;

    @Override
    public String getEncryptionValue() {
        return this.transData == null ? "" : this.transData;
    }
}
