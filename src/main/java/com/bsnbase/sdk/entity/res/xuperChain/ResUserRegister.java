package com.bsnbase.sdk.entity.res.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResUserRegister implements IBody{
    String userId;
    String userAddr;
	@Override
	public String getEncryptionValue() {
        return (this.userId == null? "":this.userId) +(this.userAddr==null?"":this.userAddr);
	}
}
