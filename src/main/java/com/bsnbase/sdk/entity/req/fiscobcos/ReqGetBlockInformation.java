package com.bsnbase.sdk.entity.req.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqGetBlockInformation implements IBody  {
    String blockNumber;
    String blockHash;
	@Override
	public String getEncryptionValue() {
		return (this.blockNumber == null? "":this.blockNumber)+(this.blockHash == null? "":this.blockHash);
	}
}
