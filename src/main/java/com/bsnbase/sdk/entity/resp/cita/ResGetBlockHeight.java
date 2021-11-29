package com.bsnbase.sdk.entity.resp.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of get block height interface
 */
@Data
public class ResGetBlockHeight implements IBody {
    /**
     * Block height
     * Note: Null when code is not 0
     */
    String data;

    @Override
    public String getEncryptionValue() {
        String str = this.data == null ? "" : this.data;
        return str;
    }
}
