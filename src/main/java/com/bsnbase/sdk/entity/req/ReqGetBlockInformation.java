package com.bsnbase.sdk.entity.req;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ReqGetBlockInformation implements IBody  {
    Integer blockNumber;
    String blockHash;
    String txId;
	@Override
	public String getEncryptionValue() {
		return (this.blockNumber == null? "0":this.blockNumber)+(this.blockHash == null? "":this.blockHash)+(this.txId == null? "":this.txId);
	}
}
