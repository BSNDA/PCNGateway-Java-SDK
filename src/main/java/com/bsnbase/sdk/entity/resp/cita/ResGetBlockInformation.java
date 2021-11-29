package com.bsnbase.sdk.entity.resp.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of get block information interface
 */

@Data
public class ResGetBlockInformation implements IBody {
    /**
     * Parameter: block hash
     * Note: Cannot be null when blockNumber is null
     */
    String blockHash;
    /**
     * Parameter: block number
     * Note: Cannot be null when blockHash is null
     */
    int blockNumber;
    /**
     * Previous block hash
     */
    String prevBlockHash;
    /**
     * Parameter: block time
     * Note: Timestamp in milliseconds format
     */
    String blockTime;
    /**
     * The cost of quotas used to excecute the transactions in the block
     */
    String quotaUsed;
    /**
     * Transaction root hash
     */
    String transactionsRoot;
    /**
     * Status root hash
     */
    String stateRoot;
    /**
     * Receipt root hash
     */
    String receiptsRoot;
    /**
     * Transaction information
     */
    Transactions[] transactions;

    public ResGetBlockInformation() {
        this.transactions = new Transactions[]{};
    }

    @Override
    public String getEncryptionValue() {
        String str = (this.blockHash == null ? "" : this.blockHash)
                + this.blockNumber
                + (this.prevBlockHash == null ? "" : this.prevBlockHash)
                + (this.blockTime == null ? "" : this.blockTime)
                + (this.quotaUsed == null ? "" : this.quotaUsed)
                + (this.transactionsRoot == null ? "" : this.transactionsRoot)
                + (this.stateRoot == null ? "" : this.stateRoot)
                + (this.receiptsRoot == null ? "" : this.receiptsRoot);

        for (int i = 0; i < this.transactions.length; i++) {
            Transactions transaction = this.transactions[i];
            str += (transaction.txHash == null ? "" : transaction.txHash)
                    + (transaction.data == null ? "" : transaction.data)
                    + (transaction.chainId == null ? "" : transaction.chainId)
                    + (transaction.quota == null ? "" : transaction.quota)
                    + (transaction.from == null ? "" : transaction.from)
                    + (transaction.to == null ? "" : transaction.to)
                    + (transaction.nonce == null ? "" : transaction.nonce)
                    + (transaction.validUntilBlock == null ? "" : transaction.validUntilBlock)
                    + (transaction.version == null ? "" : transaction.version);
        }
        return str;
    }
}
