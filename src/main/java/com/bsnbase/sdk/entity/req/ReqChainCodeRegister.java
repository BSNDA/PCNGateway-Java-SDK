package com.bsnbase.sdk.entity.req;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ReqChainCodeRegister implements IBody  {
    String chainCode;
    String eventKey;
    String notifyUrl;
    String attachArgs;
	@Override
	public String getEncryptionValue() {
		return (this.chainCode == null? "":this.chainCode) +(this.eventKey==null?"":this.eventKey)+
				(this.notifyUrl==null?"":this.notifyUrl)+(this.attachArgs==null?"":this.attachArgs);
	}
}
