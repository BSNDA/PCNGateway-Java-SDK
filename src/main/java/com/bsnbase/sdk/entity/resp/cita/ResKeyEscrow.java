package com.bsnbase.sdk.entity.resp.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of invoke smart contract in Key Trust Mode interface
 */

@Data
public class ResKeyEscrow implements IBody {
    /**
     * Transaction hash
     */
    String txId;
    /**
     * Transaction status
     */
    String status;
    /**
     * Query data
     * Return when the conract queries method
     */
    Object data;


    @Override
    public String getEncryptionValue() {
        String str = (this.txId == null ? "" : this.txId) + (this.status == null ? "" : this.status) + (this.data == null ? "" : this.data);
        return str;

    }
}
