package com.bsnbase.sdk.entity.resp.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of get block information interface
 */
@Data
public class ResGetBlockInformation implements IBody {
    /**
     * Parameter: Block hash
     * Note: cannot be null when blockNumber is null
     */
    String blockHash;
    /**
     * Block number
     * Note: cannot be null when blockHash is null
     */
    int blockNumber;
    /**
     * Previous block hash
     */
    String preBlockHash;
    /**
     * Block size
     * Byte
     */
    long blockSize;
    /**
     * Total number of transactions in the current block
     */
    int blockTxCount;

    /**
     * Transaction details
     */
    ResGetBlockTranaction[] transactions;

    public ResGetBlockInformation() {
        this.transactions = new ResGetBlockTranaction[]{};
    }

    @Override
    public String getEncryptionValue() {
        String str = "";

        str += this.blockHash == null ? "" : this.blockHash;
        str += this.blockNumber;
        str += this.preBlockHash == null ? "" : this.preBlockHash;
        str += this.blockSize;
        str += this.blockTxCount;

        for (int i = 0; i < this.transactions.length; i++) {
            ResGetBlockTranaction trans = this.transactions[i];
            str += (trans.txId == null ? "" : trans.txId);
            str += trans.status;
            str += (trans.createName == null ? "" : trans.createName);
            str += trans.timeSpanSec;
            str += trans.timeSpanNsec;
        }

        return str;
    }
}
