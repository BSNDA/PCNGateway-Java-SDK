package com.bsnbase.sdk.entity.resp.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

import java.math.BigInteger;

/**
 * Response parameters of get block information interface
 */
@Data
public class ResGetBlockInformation implements IBody {
    /**
     * Block hash
     */
    String blockHash;
    /**
     * Block number
     */
    int blockNumber;
    /**
     * Previous block hash
     */
    String parentBlockHash;
    /**
     * Block size
     */
    int blockSize;
    /**
     * Parameter: block time
     * Note: Timestamp in milliseconds format
     */
    BigInteger blockTime;
    /**
     * Author
     */
    String author;
    /**
     * Transaction information
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
        str += this.parentBlockHash == null ? "" : this.parentBlockHash;
        str += this.blockSize;
        str += this.blockTime;
        str += this.author == null ? "" : this.author;
        for (int i = 0; i < this.transactions.length; i++) {
            ResGetBlockTranaction trans = this.transactions[i];
            str += (trans.txId == null ? "" : trans.txId);
            str += (trans.blockHash == null ? "" : trans.blockHash);
            str += trans.blockNumber;
            str += trans.gasUsed;
            str += (trans.from == null ? "" : trans.from);
            str += (trans.to == null ? "" : trans.to);
            str += trans.value;
            str += (trans.input == null ? "" : trans.input);
        }

        return str;
    }
}
