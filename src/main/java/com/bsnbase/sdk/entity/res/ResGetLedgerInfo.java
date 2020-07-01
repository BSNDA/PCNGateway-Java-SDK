package com.bsnbase.sdk.entity.res;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ResGetLedgerInfo implements IBody{
    String blockHash;
    Long height;
    String preBlockHash;
    
	@Override
	public String getEncryptionValue() {
		return (this.blockHash == null?"":this.blockHash)
				+ (this.height == null?"":this.height)
				+ (this.preBlockHash == null?"":this.preBlockHash);
	}
}
