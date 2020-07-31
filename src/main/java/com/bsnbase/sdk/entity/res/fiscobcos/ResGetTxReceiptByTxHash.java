package com.bsnbase.sdk.entity.res.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResGetTxReceiptByTxHash implements IBody  {
	String txId;
	String blockHash;
	int blockNumber;
	int gasUsed;
	String from;
	String to;
	String contractAddress;
	String logs;
	@Override
	public String getEncryptionValue()
	{
		return (this.txId == null?"":this.txId)
				+ (this.blockHash == null?"":this.blockHash)
				+ (this.blockNumber)
				+ (this.gasUsed)
				+ (this.from == null?"":this.from)
				+ (this.to == null?"":this.to)
				+ (this.contractAddress == null?"":this.contractAddress)
				+ (this.logs == null?"":this.logs);

	}
}
