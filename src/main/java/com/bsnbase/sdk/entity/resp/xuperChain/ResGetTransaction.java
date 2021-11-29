package com.bsnbase.sdk.entity.resp.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of get transaction informaiton interface
 */

@Data
public class ResGetTransaction implements IBody {
    /**
     * Transaction hash
     */
    String txId;
    /**
     * Block hash
     */
    String blockId;
    /**
     * Transaction version
     */
    String version;
    /**
     * Contract's request data
     */
    ContractRequestData[] contractRequests;
    /**
     * Timestamp when receiving the transaction
     */
    long receivedTimestamp;

    public ResGetTransaction() {
        this.contractRequests = new ContractRequestData[]{};
    }

    @Override
    public String getEncryptionValue() {
        String str = (this.txId == null ? "" : this.txId)
                + (this.blockId == null ? "" : this.blockId)
                + (this.version == null ? "" : this.version);
        for (int i = 0; i < this.contractRequests.length; i++) {
            ContractRequestData contractRequestData = this.contractRequests[i];
            str += (contractRequestData.contractName == null ? "" : contractRequestData.contractName);
            str += (contractRequestData.methodName == null ? "" : contractRequestData.methodName);
            str += (contractRequestData.args == null ? "" : contractRequestData.args);
        }
        str += receivedTimestamp;
        return str;
    }
}
