package com.bsnbase.sdk.entity.req;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ReqHeader implements IBody  {
    String userCode;
    String appCode;
   // String tId;
    
	@Override
	public String getEncryptionValue() {
		return (this.userCode == null? "":this.userCode)+(this.appCode == null? "":this.appCode);
	}

};
