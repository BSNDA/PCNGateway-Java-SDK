package com.bsnbase.sdk.entity.req.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqChainCodeCancel implements IBody  {
	String eventId;
	@Override
	public String getEncryptionValue() {
		return  (this.eventId == null? "":this.eventId);
	}
}
