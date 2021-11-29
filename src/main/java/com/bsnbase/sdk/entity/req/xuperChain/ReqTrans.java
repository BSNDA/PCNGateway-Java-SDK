package com.bsnbase.sdk.entity.req.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of invoke the smart contract in Public Key Upload Mode interface
 */
@Data
public class ReqTrans implements IBody {
    /**
     * Contract name
     */
    String Initiator;
    /**
     * Transaction initiator
     * Can be an Address or an Account
     */
    String TransData;
    /**
     * Execution code
     * 0：Pre-execute 1：execute
     */
    int flag;

    @Override
    public String getEncryptionValue() {
        return (this.Initiator == null ? "" : this.Initiator)
                + (this.TransData == null ? "" : this.TransData)
                + (this.flag);
    }
}
