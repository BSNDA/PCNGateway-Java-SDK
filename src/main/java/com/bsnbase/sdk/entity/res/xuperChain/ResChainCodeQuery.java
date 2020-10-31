package com.bsnbase.sdk.entity.res.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResChainCodeQuery implements IBody{
    BlockEvent[] blockEvent;
	public ResChainCodeQuery(){
		this.blockEvent = new BlockEvent[]{};
	}

	@Override
	public String getEncryptionValue() {
		String str = "";

		for (int i = 0; i<this.blockEvent.length; i++) {
			BlockEvent blockEvent = this.blockEvent[i];
			str += (blockEvent.eventId == null ? "" : blockEvent.eventId);
			str += (blockEvent.eventKey == null ? "" : blockEvent.eventKey);
			str += (blockEvent.appCode == null ? "" : blockEvent.appCode);
			str += (blockEvent.userCode == null ? "" : blockEvent.userCode);
			str += (blockEvent.contractName == null ? "" : blockEvent.contractName);
			str += (blockEvent.notifyUrl == null ? "" : blockEvent.notifyUrl);
			str += (blockEvent.attachArgs == null ? "" : blockEvent.attachArgs);
			str += (blockEvent.createTime == null ? "" : blockEvent.createTime);
		}
		return str;
	}
    
}
