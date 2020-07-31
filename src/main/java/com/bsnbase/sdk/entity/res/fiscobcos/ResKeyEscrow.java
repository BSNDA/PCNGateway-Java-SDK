package com.bsnbase.sdk.entity.res.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResKeyEscrow implements IBody{
    boolean constant;
    String queryInfo;
    String txId;
    String blockHash;
	Integer blockNumber;
	Integer gasUsed;
    String status;
    String from;
    String to;
    String input;
    String output;

	@Override
	public String getEncryptionValue() {
		StringBuffer str=new StringBuffer();
		if(constant){
			str.append(this.queryInfo == null?"":this.queryInfo);
		}else{
			str.append(this.txId == null?"":this.txId)
		       .append(this.blockHash == null?"":this.blockHash)
			   .append(this.blockNumber == null?"":this.blockNumber)
			   .append(this.gasUsed == null?"":this.gasUsed)
			   .append(this.status == null?"":this.status)
			   .append(this.from == null?"":this.from)
			   .append(this.to == null?"":this.to)
			   .append(this.input == null?"":this.input)
			   .append(this.output == null?"":this.output);
		}
		return str.toString();

	}
}
