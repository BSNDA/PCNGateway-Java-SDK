package com.bsnbase.sdk.entity.res.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResGetBlockData implements IBody {
    String blockHash;
    Long blockNumber;
    String preBlockHash;
    String blockData;

    @Override
    public String getEncryptionValue() {
        return (this.blockHash == null ? "" : this.blockHash)
                + (this.blockNumber == null ? "" : this.blockNumber)
                + (this.preBlockHash == null ? "" : this.preBlockHash)
                + (this.blockData == null ? "" : this.blockData);
    }
}
