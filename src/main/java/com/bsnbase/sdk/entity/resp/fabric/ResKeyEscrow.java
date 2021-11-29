package com.bsnbase.sdk.entity.resp.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of invoke the smart contract in Key Trust Mode
 */

@Data
public class ResKeyEscrow implements IBody {
    /**
     * Block information
     * Note: Null when the code is not 0
     */
    BlockInfo blockInfo;
    /**
     * Chaincode response message
     * Note: Null when the code is not 0
     */
    CcRes ccRes;

    @Override
    public String getEncryptionValue() {


        return (this.blockInfo.getTxId() == null ? "" : this.blockInfo.getTxId())
                + (this.blockInfo.getBlockHash() == null ? "" : this.blockInfo.getBlockHash())
                + (this.blockInfo.getStatus() == null ? "" : this.blockInfo.getStatus())
                + (this.ccRes.getCcCode() == null ? "" : this.ccRes.getCcCode())
                + (this.ccRes.getCcData() == null ? "" : this.ccRes.getCcData());
    }
}
