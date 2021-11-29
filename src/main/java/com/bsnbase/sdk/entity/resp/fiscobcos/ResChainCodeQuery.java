package com.bsnbase.sdk.entity.resp.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of query chaincode event interface
 */

@Data
public class ResChainCodeQuery implements IBody {
    /**
     * Block event
     * Note: Null when the code is not 0
     */
    BlockEvent[] blockEvent;
    /**
     * Contract event
     */
    BlockContractEvent[] contractEvent;

    public ResChainCodeQuery() {
        this.blockEvent = new BlockEvent[]{};
        this.contractEvent = new BlockContractEvent[]{};
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
            BlockContractEvent blockContractEvent = this.contractEvent[i];
            str += (blockContractEvent.eventId == null ? "" : blockContractEvent.eventId);
            str += (blockContractEvent.appcode == null ? "" : blockContractEvent.appcode);
            str += (blockContractEvent.userCode == null ? "" : blockContractEvent.userCode);
            str += (blockContractEvent.notifyUrl == null ? "" : blockContractEvent.notifyUrl);
            str += (blockContractEvent.attachArgs == null ? "" : blockContractEvent.attachArgs);
            str += (blockContractEvent.createTime == null ? "" : blockContractEvent.createTime);
            str += (blockContractEvent.contractAddress == null ? "" : blockContractEvent.contractAddress);
        }

        return str;
    }

}
