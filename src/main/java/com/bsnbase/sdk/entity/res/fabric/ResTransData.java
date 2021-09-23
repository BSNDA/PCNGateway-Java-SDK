package com.bsnbase.sdk.entity.res.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResTransData implements IBody  {
    String txId;
	String transData;
	@Override
	public String getEncryptionValue() {
		return (this.txId == null ? "" : this.txId)+ (this.transData == null ? "" : this.transData);
	}
}
