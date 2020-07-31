package com.bsnbase.sdk.entity.req.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqChainCodeRegister implements IBody  {
    int eventType;
    String contractAddress;
    String contractName;
    String notifyUrl;
	String attachArgs;
	@Override
	public String getEncryptionValue() {
		return (this.eventType) +(this.contractAddress==null?"":this.contractAddress)+
				(this.contractName==null?"":this.contractName)+(this.notifyUrl==null?"":this.notifyUrl)
				+(this.attachArgs==null?"":this.attachArgs);
	}
}
