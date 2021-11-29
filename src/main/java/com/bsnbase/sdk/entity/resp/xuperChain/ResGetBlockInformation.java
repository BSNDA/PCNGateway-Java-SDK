package com.bsnbase.sdk.entity.resp.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of get block informaiton interface
 */
@Data
public class ResGetBlockInformation implements IBody {
    /**
     * Block version
     */
    int version;
    /**
     * Block hash
     */
    String blockid;
    /**
     * Previous block hash
     */
    String preHash;
    /**
     * Block height
     */
    int height;
    /**
     * Timestamp of the block
     */
    long timestamp;
    /**
     * Transactions
     */
    ResGetTransaction[] transactions;
    /**
     * The number of transactions in the block
     */
    int txCount;
    /**
     * Next block hash
     */
    String nextHash;

    public ResGetBlockInformation() {
        this.transactions = new ResGetTransaction[]{};
    }

    @Override
    public String getEncryptionValue() {
        String str = (version)
                + (this.blockid == null ? "" : this.blockid)
                + (this.preHash == null ? "" : this.preHash)
                + (this.height)
                + (this.timestamp);
        for (int i = 0; i < this.transactions.length; i++) {
            ResGetTransaction resGetTransaction = this.transactions[i];
            str += (resGetTransaction.getEncryptionValue());
        }
        str += txCount;
        str += this.nextHash == null ? "" : this.nextHash;
        return str;
    }
}
