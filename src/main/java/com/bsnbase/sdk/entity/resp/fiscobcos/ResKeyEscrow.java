package com.bsnbase.sdk.entity.resp.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of invoke smart contract in Key Trust Mode interface
 */

@Data
public class ResKeyEscrow implements IBody {
    /**
     * Call type
     */
    boolean constant;
    /**
     * Query information
     * Note: This field has value when Constant is true
     */
    String queryInfo;
    /**
     * Transaction hash
     * Note: This field has value and is valid when Constant is false
     */
    String txId;
    /**
     * Block hash
     * Note: This field has value and is valid when Constant is false
     */
    String blockHash;
    /**
     * Block number
     * Note: This field has value and is valid when Constant is false
     */
    Integer blockNumber;
    /**
     * Gas used value
     * Note: This field has value and is valid when Constant is false
     */
    Integer gasUsed;
    /**
     * Transaction status
     * Note: Note: This field has value and is valid when Constant is false, 0x0 means success, the staus refers to transaction receipt status.
     */
    String status;
    /**
     * Transaction sender
     * Note: This field has value and is valid when Constant is false
     */
    String from;
    /**
     * Transaction receiver
     * Note: This field has value and is valid when Constant is false
     */
    String to;
    /**
     * Input
     * Note: This field has value and is valid when Constant is false
     */
    String input;
    /**
     * Output
     * Note: This field has value and is valid when Constant is false
     */
    String output;
    /**
     * Log
     * Note: When contract contains event, this field returns the content of the event.
     */
    String logs;

    @Override
    public String getEncryptionValue() {
        StringBuffer str = new StringBuffer();
        str.append(constant)
                .append(this.queryInfo == null ? "" : this.queryInfo)
                .append(this.txId == null ? "" : this.txId)
                .append(this.blockHash == null ? "" : this.blockHash)
                .append(this.blockNumber == null ? "" : this.blockNumber)
                .append(this.gasUsed == null ? "" : this.gasUsed)
                .append(this.status == null ? "" : this.status)
                .append(this.from == null ? "" : this.from)
                .append(this.to == null ? "" : this.to)
                .append(this.input == null ? "" : this.input)
                .append(this.output == null ? "" : this.output)
                .append(this.logs == null ? "" : this.logs);
        return str.toString();

    }
}
