package com.bsnbase.sdk.entity.res.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResGetTxCount implements IBody{
	String data;
	@Override
	public String getEncryptionValue() {
		String str = this.data==null?"":this.data;
		return str;
	}
}
