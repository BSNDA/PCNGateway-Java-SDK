package com.bsnbase.sdk.entity.req.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqGetTxCountByBlockNumber implements IBody  {
	String blockNumber;

	@Override
	public String getEncryptionValue() {
		return  (this.blockNumber == null? "":this.blockNumber);
	}
}
