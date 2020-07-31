package com.bsnbase.sdk.entity.res.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResTrans implements IBody {
    boolean constant;
    String queryInfo;
    String txId;
    String blockHash;
    Integer blockNumber;
    Integer gasUsed;
    String status;
    String from;
    String to;
    String input;
    String output;
    String logs;

    @Override
    public String getEncryptionValue() {
        StringBuffer str = new StringBuffer();
        str.append(constant ? "true" : "false")
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
