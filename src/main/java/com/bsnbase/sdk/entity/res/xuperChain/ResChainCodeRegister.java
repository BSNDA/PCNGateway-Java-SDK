package com.bsnbase.sdk.entity.res.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResChainCodeRegister implements IBody {
    String eventId;
	@Override
	public String getEncryptionValue() {
		return (this.eventId == null?"":this.eventId);
	}
}
