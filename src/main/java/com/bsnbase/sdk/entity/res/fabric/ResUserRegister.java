package com.bsnbase.sdk.entity.res.fabric;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ResUserRegister implements IBody{
    String name;
    String secret;
	@Override
	public String getEncryptionValue() {
        return (this.name == null? "":this.name) +(this.secret==null?"":this.secret) ;
	}
}
