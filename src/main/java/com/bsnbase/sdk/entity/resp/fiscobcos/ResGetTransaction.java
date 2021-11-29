package com.bsnbase.sdk.entity.resp.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of get transaction information interface
 */
@Data
public class ResGetTransaction implements IBody {
    /**
     * Transaction hash
     */
    String txId;
    /**
     * Block hash
     */
    String blockHash;
    /**
     * Block number
     */
    int blockNumber;
    /**
     * Gas used
     */
    int gasUsed;
    /**
     * Transaction sender
     */
    String from;
    /**
     * Transaction receiver
     */
    String to;
    /**
     * Transferred value
     */
    int value;
    /**
     * Input of the transaction
     */
    String input;

    @Override
    public String getEncryptionValue() {
        return (this.txId == null ? "" : this.txId)
                + (this.blockHash == null ? "" : this.blockHash)
                + (this.blockNumber)
                + (this.gasUsed)
                + (this.from == null ? "" : this.from)
                + (this.to == null ? "" : this.to)
                + (this.value)
                + (this.input == null ? "" : this.input);
    }
}
