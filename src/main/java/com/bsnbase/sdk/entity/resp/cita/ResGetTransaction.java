package com.bsnbase.sdk.entity.resp.cita;

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
    String txHash;
    /**
     * Block hash
     */
    String blockHash;
    /**
     * Block number
     */
    int blockNumber;
    /**
     * Transaction data
     */
    String data;
    /**
     * Chain ID
     */
    String chainId;
    /**
     * Transaction quota
     */
    String quota;
    /**
     * Transaction sender
     */
    String from;
    /**
     * Transaction receiver
     */
    String to;
    /**
     * Nonce
     */
    String nonce;
    /**
     * The maximum block height for on-chain transactions
     */
    String validUntilBlock;
    /**
     * version
     */
    String version;

    @Override
    public String getEncryptionValue() {
        return (this.txHash == null ? "" : this.txHash)
                + (this.blockHash == null ? "" : this.blockHash)
                + (this.blockNumber)
                + (this.data == null ? "" : this.data)
                + (this.chainId == null ? "" : this.chainId)
                + (this.quota == null ? "" : this.quota)
                + (this.from == null ? "" : this.from)
                + (this.to == null ? "" : this.to)
                + (this.nonce == null ? "" : this.nonce)
                + (this.validUntilBlock == null ? "" : this.validUntilBlock)
                + (this.version == null ? "" : this.version);

    }
}
