package com.bsnbase.sdk.entity.req.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqTrans implements IBody {
    String Initiator;
    String TransData;
    int    flag;
    @Override
    public String getEncryptionValue() {
        return (this.Initiator == null ? "" : this.Initiator)
                +(this.TransData == null ? "" : this.TransData)
                + (this.flag);
    }
}
