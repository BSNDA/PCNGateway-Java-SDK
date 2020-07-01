package com.bsnbase.sdk.entity.req;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ReqKeyEscrowNo implements IBody  {
    String transData;
	@Override
	public String getEncryptionValue() {
		return this.transData == null?"":this.transData;
	}
}
