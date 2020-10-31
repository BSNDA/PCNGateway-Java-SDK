package com.bsnbase.sdk.entity.res.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResGetTxReceiptByTxHash implements IBody  {
	String transactionHash;
	Integer transactionIndex;
	String blockHash;
	Long blockNumber;
	String cumulativeGasUsed;
	Long cumulativeQuotaUsed;
	String gasUsed;
	Long quotaUsed;
	String contractAddress;
	String root;
	String status;
	String from;
	String to;
	String logsBloom;
	String errorMessage;
	String transactionIndexRaw;
	String blockNumberRaw;
	String cumulativeGasUsedRaw;
	String cumulativeQuotaUsedRaw;
	String gasUsedRaw;
	String quotaUsedRaw;

	Log[] logs;
	public ResGetTxReceiptByTxHash(){
		this.logs = new  Log[]{};
	}

	@Override
	public String getEncryptionValue() {
		String str="";
		str+= (this.transactionHash == null?"":this.transactionHash)
				+ (this.transactionIndex == null?"":this.transactionIndex)
				+ (this.blockHash == null?"":this.blockHash)
				+ (this.blockNumber == null?"":this.blockNumber)
				+ (this.cumulativeGasUsed == null?"":this.cumulativeGasUsed)
				+ (this.cumulativeQuotaUsed == null?"":this.cumulativeQuotaUsed)
				+ (this.gasUsed == null?"":this.gasUsed)
				+ (this.quotaUsed == null?"":this.quotaUsed)
				+ (this.contractAddress == null?"":this.contractAddress)
				+ (this.root == null?"":this.root)
				+ (this.status == null?"":this.status)
				+ (this.from == null?"":this.from)
				+ (this.to == null?"":this.to)
				+ (this.logsBloom == null?"":this.logsBloom)
				+ (this.errorMessage == null?"":this.errorMessage)
				+ (this.transactionIndexRaw == null?"":this.transactionIndexRaw)
				+ (this.blockNumberRaw == null?"":this.blockNumberRaw)
				+ (this.cumulativeGasUsedRaw == null?"":this.cumulativeGasUsedRaw)
				+ (this.cumulativeQuotaUsedRaw == null?"":this.cumulativeQuotaUsedRaw)
				+ (this.gasUsedRaw == null?"":this.gasUsedRaw)
				+ (this.quotaUsedRaw == null?"":this.quotaUsedRaw);

		for (int i=0;i<this.logs.length;i++) {
			Log log = this.logs[i];
			str += (log.removed == null ? "" : log.removed);
			str += (log.logIndex == null ? "" : log.logIndex);
			str += (log.transactionIndex == null ? "" : log.transactionIndex);
			str += (log.transactionHash == null ? "" : log.transactionHash);
			str += (log.blockHash == null ? "" : log.blockHash);
			str += (log.blockNumber == null ? "" : log.blockNumber);
			str += (log.address == null ? "" : log.address);
			str += (log.data == null ? "" : log.data);
			str += (log.transactionLogIndex == null ? "" : log.transactionLogIndex);
			str += (log.transactionIndexRaw == null ? "" : log.transactionIndexRaw);
			str += (log.blockNumberRaw == null ? "" : log.blockNumberRaw);
			str += (log.logIndexRaw == null ? "" : log.logIndexRaw);
			for (String topic : log.topics) {
				str +=topic;
			}
		}
		return  str;

	}
}
