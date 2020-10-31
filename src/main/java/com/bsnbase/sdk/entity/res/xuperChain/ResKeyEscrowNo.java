package com.bsnbase.sdk.entity.res.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResKeyEscrowNo implements IBody{
	String txId;
	String queryInfo;
	String PreExecRes;

	@Override
	public String getEncryptionValue() {
		return (this.txId == null?"":this.txId)
				+ (this.queryInfo == null?"":this.queryInfo)
				+ (this.PreExecRes == null?"":this.PreExecRes);
	}
}
