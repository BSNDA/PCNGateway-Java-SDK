package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqGetTxReceiptByTxHash implements IBody  {
	String txHash;
	@Override
	public String getEncryptionValue() {
		return this.txHash == null? "":this.txHash;
	}
}
