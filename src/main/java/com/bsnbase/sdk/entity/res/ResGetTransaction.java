package com.bsnbase.sdk.entity.res;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ResGetTransaction implements IBody{
    String blockHash;
    Long blockNumber;
    Integer status;
    String createName;
    String timeSpanSec;
    String timeSpanNsec;
	@Override
	public String getEncryptionValue() {
		return (this.blockHash == null?"":this.blockHash)
				+ (this.blockNumber == null?"":this.blockNumber)
				+ (this.status == null?"":this.status)
				+ (this.createName == null?"":this.createName)
				+ (this.timeSpanSec == null?"":this.timeSpanSec)
				+ (this.timeSpanNsec == null?"":this.timeSpanNsec);
	}
}
