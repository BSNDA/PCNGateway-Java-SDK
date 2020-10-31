package com.bsnbase.sdk.entity.res.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResGetTransaction implements IBody{
    String txHash;
    String data;
    String chainId;
    String quota;
	String from;
	String to;
	String nonce;
	String validUntilBlock;
	String version;

	@Override
	public String getEncryptionValue() {
		return (this.txHash == null?"":this.txHash)
				+ (this.data == null?"":this.data)
				+ (this.chainId == null?"":this.chainId)
				+ (this.quota == null?"":this.quota)
				+ (this.from == null?"":this.from)
				+ (this.to == null?"":this.to)
				+ (this.nonce == null?"":this.nonce)
				+ (this.validUntilBlock == null?"":this.validUntilBlock)
				+ (this.version == null?"":this.version);

	}
}
