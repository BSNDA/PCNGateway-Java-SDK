package com.bsnbase.sdk.entity.req.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of invoke the smart contract in Key Trust Mode interface
 */
@Data
public class ReqKeyEscrow implements IBody {
    /**
     * User ID
     */
    String userId;
    /**
     * User address
     */
    String userAddr;
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
     * Note: String, parameters convert to json string
     */
    String funcParam;

    @Override
    public String getEncryptionValue() {

        String str = (this.userId == null ? "" : this.userId);
        str += (this.userAddr == null ? "" : this.userAddr);
        str += (this.contractName == null ? "" : this.contractName);
        str += (this.funcName == null ? "" : this.funcName);
        str += (this.funcParam == null ? "" : this.funcParam);
        return str;
    }
}
