package com.bsnbase.sdk.entity.res;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ResKeyEscrowEnroll implements IBody{

	@JSONField(name  = "cert")
    String cert;
    @Override
	public String getEncryptionValue() {
    	return (this.cert == null?"":this.cert);
	}
}
