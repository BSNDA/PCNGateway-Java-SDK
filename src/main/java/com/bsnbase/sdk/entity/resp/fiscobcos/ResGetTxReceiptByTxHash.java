package com.bsnbase.sdk.entity.resp.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of get transaction receipt interface
 */
@Data
public class ResGetTxReceiptByTxHash implements IBody {
    /**
     * Transaction ID
     * Note: Null when code is not 0
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
     * Note: The user address sending transactions when invoking the interface.
     */
    String from;
    /**
     * Transaction receiver
     * Note: Contract address when invoking the interface.
     */
    String to;
    /**
     * Contract address
     * Note: The returned contract address when deploying the smart contract.
     */
    String contractAddress;
    /**
     * Log
     * Note: When contract contains event, this field returns the content of the event.
     */
    String logs;

    @Override
    public String getEncryptionValue() {
        return (this.txId == null ? "" : this.txId)
                + (this.blockHash == null ? "" : this.blockHash)
                + (this.blockNumber)
                + (this.gasUsed)
                + (this.from == null ? "" : this.from)
                + (this.to == null ? "" : this.to)
                + (this.contractAddress == null ? "" : this.contractAddress)
                + (this.logs == null ? "" : this.logs);

    }
}
