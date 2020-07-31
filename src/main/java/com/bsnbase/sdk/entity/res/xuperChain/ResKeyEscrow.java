package com.bsnbase.sdk.entity.res.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResKeyEscrow implements IBody{
    String txId;

	@Override
	public String getEncryptionValue() {
		String str=this.txId == null?"":this.txId;
		return str;

	}
}
