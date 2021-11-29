package com.bsnbase.sdk.entity.resp.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of query chaincode event interface
 */
@Data
public class ResChainCodeQuery implements IBody {
    /**
     * Parameter: block event
     * Note: Null when code is not 0
     */
    BlockEvent[] blockEvent;
    /**
     * Note: contract event
     */
    ContractEvent[] contractEvent;


    public ResChainCodeQuery() {
        this.blockEvent = new BlockEvent[]{};
        this.contractEvent = new ContractEvent[]{};
    }

    @Override
    public String getEncryptionValue() {
        String str = "";

        for (int i = 0; i < this.blockEvent.length; i++) {
            BlockEvent blockEvent = this.blockEvent[i];
            str += (blockEvent.eventId == null ? "" : blockEvent.eventId);
            str += (blockEvent.appcode == null ? "" : blockEvent.appcode);
            str += (blockEvent.userCode == null ? "" : blockEvent.userCode);
            str += (blockEvent.notifyUrl == null ? "" : blockEvent.notifyUrl);
            str += (blockEvent.attachArgs == null ? "" : blockEvent.attachArgs);
            str += (blockEvent.createTime == null ? "" : blockEvent.createTime);
        }
        for (int i = 0; i < this.contractEvent.length; i++) {
            ContractEvent contractEvent = this.contractEvent[i];
            str += (contractEvent.eventId == null ? "" : contractEvent.eventId);
            str += (contractEvent.appcode == null ? "" : contractEvent.appcode);
            str += (contractEvent.userCode == null ? "" : contractEvent.userCode);
            str += (contractEvent.notifyUrl == null ? "" : contractEvent.notifyUrl);
            str += (contractEvent.attachArgs == null ? "" : contractEvent.attachArgs);
            str += (contractEvent.createTime == null ? "" : contractEvent.createTime);
            str += (contractEvent.contractAddress == null ? "" : contractEvent.contractAddress);

        }
        return str;
    }

}
