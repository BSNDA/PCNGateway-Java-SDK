package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

import java.util.Map;

/**
 * Request parameters of invoking chaincode
 * Public Key Upload Mode or Key Trust Mode
 */
@Data
public class ReqKeyEscrow implements IBody {
    /**
     * Username
     */
    String userName;
    /**
     * Nonce
     * Note: 24-bit random byte data using base64 encoding
     */
    String nonce;
    /**
     * Chaincode
     */
    String chainCode;
    /**
     * Method name
     */
    String funcName;
    /**
     * Request parameters
     */
    String[] args;
    /**
     * Tansient data
     */
    Map<String, String> transientData;

    @Override
    public String getEncryptionValue() {

        String str = (this.userName == null ? "" : this.userName);
        str += (this.nonce == null ? "" : this.nonce);
        str += (this.chainCode == null ? "" : this.chainCode);
        str += (this.funcName == null ? "" : this.funcName);

        for (int i = 0; i < this.args.length; i++) {
            str += this.args[i];
        }

        if (this.transientData != null) {
            for (Map.Entry<String, String> entry : this.transientData.entrySet()) {
                String mapKey = entry.getKey();
                String mapValue = entry.getValue();
                str = str + mapKey + mapValue;
            }
        }

        return str;
    }
}
