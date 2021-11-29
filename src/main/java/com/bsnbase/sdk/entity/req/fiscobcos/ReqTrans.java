package com.bsnbase.sdk.entity.req.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters for FISCO-BCOS calling the smart contract interface in Public Key Upload Mode
 */
@Data
public class ReqTrans implements IBody {
    /**
     * Contract name
     */
    String contractName;
    /**
     * Transaction data
     */
    String transData;

    @Override
    public String getEncryptionValue() {
        return (this.contractName == null ? "" : this.contractName)
                + (this.contractName == null ? "" : this.transData);
    }
}
