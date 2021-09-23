package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqTransData implements IBody  {
    String txId;
    @Override
    public String getEncryptionValue() {
        return this.txId == null?"":this.txId;
    }
}
