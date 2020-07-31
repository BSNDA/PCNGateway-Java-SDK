package com.bsnbase.sdk.entity.res.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResUserRegister implements IBody{
    String userId;
    String userAddress;
	@Override
	public String getEncryptionValue() {
        return (this.userId == null? "":this.userId) +(this.userAddress==null?"":this.userAddress);
	}
}
