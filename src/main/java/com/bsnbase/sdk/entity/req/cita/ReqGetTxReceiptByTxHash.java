package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get transaction receipt interface
 */
@Data
public class ReqGetTxReceiptByTxHash implements IBody {
    /**
     * Transaction hash
     */
    String txHash;

    @Override
    public String getEncryptionValue() {
        return this.txHash == null ? "" : this.txHash;
    }
}
