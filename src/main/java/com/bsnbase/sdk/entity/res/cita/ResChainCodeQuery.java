package com.bsnbase.sdk.entity.res.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResChainCodeQuery implements IBody{
    BlockEvent[] blockEvent;
	ContractEvent[] contractEvent;
	public ResChainCodeQuery(){
		this.blockEvent = new BlockEvent[]{};
		this.contractEvent = new ContractEvent[]{};
	}

	@Override
	public String getEncryptionValue() {
		String str = "";

		for (int i = 0; i<this.blockEvent.length; i++) {
			BlockEvent blockEvent = this.blockEvent[i];
			str += (blockEvent.eventId == null ? "" : blockEvent.eventId);
			str += (blockEvent.appcode == null ? "" : blockEvent.appcode);
			str += (blockEvent.userCode == null ? "" : blockEvent.userCode);
			str += (blockEvent.notifyUrl == null ? "" : blockEvent.notifyUrl);
			str += (blockEvent.attachArgs == null ? "" : blockEvent.attachArgs);
			str += (blockEvent.createTime == null ? "" : blockEvent.createTime);
		}
		for (int i = 0; i<this.contractEvent.length; i++) {
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
