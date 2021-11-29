package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters in Key Trust Mode
 */
@Data
public class ReqKeyEscrow implements IBody {
    /**
     * User ID
     * User ID registered through the registration interface
     */
    String userId;
    /**
     * Contract name
     */
    String contractName;
    /**
     * Method name
     */
    String funcName;
    /**
     * Method parameters
     * Convert array format parameters to json string assignment
     */
    String funcParam;

    @Override
    public String getEncryptionValue() {

        String str = (this.userId == null ? "" : this.userId);
        str += (this.contractName == null ? "" : this.contractName);
        str += (this.funcName == null ? "" : this.funcName);
        str += (this.funcParam == null ? "" : this.funcParam);
        return str;
    }
}
