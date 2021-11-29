package com.bsnbase.sdk.entity.req.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of FISCO-BCOS get the total number of transactions interface
 */
@Data
public class ReqGetTxCountByBlockNumber implements IBody {
    /**
     * Block number
     * String format for decimal numbers
     */
    String blockNumber;

    @Override
    public String getEncryptionValue() {
        return (this.blockNumber == null ? "" : this.blockNumber);
    }
}
