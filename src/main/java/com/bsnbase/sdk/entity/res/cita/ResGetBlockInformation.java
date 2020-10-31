package com.bsnbase.sdk.entity.res.cita;

import com.bsnbase.sdk.entity.base.IBody;
import com.bsnbase.sdk.entity.res.xuperChain.ResGetTransaction;
import lombok.Data;

@Data
public class ResGetBlockInformation implements IBody{
	String  blockHash;
	int  blockNumber;
	String prevBlockHash;
	String blockTime;
	String quotaUsed;
	String transactionsRoot;
	String stateRoot;
	String receiptsRoot;
	Transactions[] transactions;
	public ResGetBlockInformation(){
		this.transactions =new Transactions[]{};
	}
	@Override
	public String getEncryptionValue() {
		String str= (this.blockHash == null?"":this.blockHash)
					+this.blockNumber
					+ (this.prevBlockHash == null?"":this.prevBlockHash)
					+ (this.blockTime== null?"":this.blockTime)
					+ (this.quotaUsed== null?"":this.quotaUsed)
					+ (this.transactionsRoot== null?"":this.transactionsRoot)
					+ (this.stateRoot== null?"":this.stateRoot)
					+ (this.receiptsRoot== null?"":this.receiptsRoot);

		for (int i = 0; i<this.transactions.length; i++) {
			Transactions transaction = this.transactions[i];
			str +=  (transaction.txHash== null?"":transaction.txHash)
		        	+(transaction.data== null?"":transaction.data)
					+(transaction.chainId== null?"":transaction.chainId)
					+(transaction.quota== null?"":transaction.quota)
					+(transaction.from== null?"":transaction.from)
					+(transaction.to== null?"":transaction.to)
					+(transaction.nonce== null?"":transaction.nonce)
					+(transaction.validUntilBlock== null?"":transaction.validUntilBlock)
					+(transaction.version== null?"":transaction.version);
		}
		return str;
	}
}
