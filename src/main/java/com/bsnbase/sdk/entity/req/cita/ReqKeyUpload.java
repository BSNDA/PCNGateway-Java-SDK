package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of invoke the smart contract in Public Key Upload Mode interface
 */

@Data
public class ReqKeyUpload implements IBody {

    /**
     * Contract name
     */
    String contractName;
    /**
     * Contract address
     */
    String contractAddr;
    /**
     * Method name
     */
    String funcName;
    /**
     * Method parameters
     */
    String[] args;


    @Override
    public String getEncryptionValue() {
        String str = (this.contractName == null ? "" : this.contractName);
        str += (this.contractAddr == null ? "" : this.contractAddr);
        str += (this.funcName == null ? "" : this.funcName);
        for (int i = 0; i < this.args.length; i++) {
            str += this.args[i];
        }
        return str;
    }
}
