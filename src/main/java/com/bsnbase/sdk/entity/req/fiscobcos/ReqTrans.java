package com.bsnbase.sdk.entity.req.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqTrans implements IBody {
    String contractName;
    String transData;

    @Override
    public String getEncryptionValue() {
        return (this.contractName == null ? "" : this.contractName)
                + (this.contractName == null ? "" : this.transData);
    }
}
