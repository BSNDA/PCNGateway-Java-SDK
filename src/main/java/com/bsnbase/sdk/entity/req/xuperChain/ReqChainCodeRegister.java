package com.bsnbase.sdk.entity.req.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqChainCodeRegister implements IBody  {
    String contractName;
    String eventKey;
    String notifyUrl;
    String attachArgs;
	@Override
	public String getEncryptionValue() {
		return (this.contractName==null?"":this.contractName)
				+(this.eventKey==null?"":this.eventKey)
				+(this.notifyUrl==null?"":this.notifyUrl)
				+(this.attachArgs==null?"":this.attachArgs);
	}
}
