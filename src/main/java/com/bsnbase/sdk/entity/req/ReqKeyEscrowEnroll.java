package com.bsnbase.sdk.entity.req;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ReqKeyEscrowEnroll implements IBody  {
    String name;
    String secret;
    String csrPem;
	@Override
	public String getEncryptionValue() {
		return (this.name == null? "":this.name)
				+(this.secret == null? "":this.secret)
		+(this.csrPem == null? "":this.csrPem);
	}
}
