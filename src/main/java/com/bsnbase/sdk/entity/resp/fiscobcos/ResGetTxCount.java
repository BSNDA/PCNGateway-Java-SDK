package com.bsnbase.sdk.entity.resp.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of get the total number of transactions interface
 */
@Data
public class ResGetTxCount implements IBody {
    /**
     * Transaction information
     * Note: Null when code is not 0
     */
    String data;

    @Override
    public String getEncryptionValue() {
        String str = this.data == null ? "" : this.data;
        return str;
    }
}
