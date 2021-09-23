package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import com.citahub.cita.abi.datatypes.Int;
import lombok.Data;

@Data
public class ReqGetBlockData implements IBody {
    Integer blockNumber;
    String blockHash;
    String txId;

    @Override
    public String getEncryptionValue() {
        return (this.blockNumber == null ? "" : this.blockNumber)
                + (this.blockHash == null ? "" : this.blockHash)
                + (this.txId == null ? "" : this.txId);
    }
}
