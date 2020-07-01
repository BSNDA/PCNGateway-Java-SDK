package com.bsnbase.sdk.entity.res;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ResHeader implements IBody{
    Integer code;
    String msg;
    
	@Override
	public String getEncryptionValue() {
		return (this.code == null?"":this.code)
				+ (this.msg == null?"":this.msg);
	}
}
