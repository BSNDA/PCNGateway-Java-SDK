package com.bsnbase.sdk.entity.resp.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;


/**
 * Response parameters of get transaction information interface
 */

@Data
public class ResGetTransaction implements IBody {
    /**
     * Block hash
     */
    String blockHash;
    /**
     * Block number
     */
    Long blockNumber;
    /**
     * Parameter: Transaction status
     * Note: See transaction status descriptions
     */
    Integer status;
    /**
     * Username
     */
    String createName;
    /**
     * Parameter: Timestamp by second
     * Note: The second part of timestamp
     */
    String timeSpanSec;
    /**
     * Parameter: Timestamp by nanosecond
     * Note: The nanosecond part of timestamp
     */
    String timeSpanNsec;

    @Override
    public String getEncryptionValue() {
        return (this.blockHash == null ? "" : this.blockHash)
                + (this.blockNumber == null ? "" : this.blockNumber)
                + (this.status == null ? "" : this.status)
                + (this.createName == null ? "" : this.createName)
                + (this.timeSpanSec == null ? "" : this.timeSpanSec)
                + (this.timeSpanNsec == null ? "" : this.timeSpanNsec);
    }
}
