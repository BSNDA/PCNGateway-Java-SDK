package com.bsnbase.sdk.entity.req.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqGetBlockInformation implements IBody  {
    int blockHeight;
	String blockHash;
	@Override
	public String getEncryptionValue() {
		return (this.blockHeight+"")+(this.blockHash == null? "":this.blockHash);
	}
}
