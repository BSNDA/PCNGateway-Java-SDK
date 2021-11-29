package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request body of invoke the smart contract in public key upload mode interface
 */
@Data
public class ReqKeyUploadBody implements IBody {
    /**
     * Contract name
     */
    private String contractName;
    /**
     * Transaction signature
     */
    private String transData;

    @Override
    public String getEncryptionValue() {
        String str = (this.contractName == null ? "" : this.contractName);
        str += (this.transData == null ? "" : this.transData);
        return str;
    }
}
