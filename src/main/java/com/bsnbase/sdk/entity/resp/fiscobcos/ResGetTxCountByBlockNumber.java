package com.bsnbase.sdk.entity.resp.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get the total number of transactions in the block interface
 */
@Data
public class ResGetTxCountByBlockNumber implements IBody {
    /**
     * Total number of transactions in the block
     * Note: Null when code is not 0
     */
    String data;

    @Override
    public String getEncryptionValue() {
        String str = this.data == null ? "" : this.data;
        return str;
    }
}
