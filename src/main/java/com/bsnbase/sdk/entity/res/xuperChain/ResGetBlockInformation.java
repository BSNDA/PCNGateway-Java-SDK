package com.bsnbase.sdk.entity.res.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import com.bsnbase.sdk.entity.res.fiscobcos.ResGetBlockTranaction;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ResGetBlockInformation implements IBody{
	int version;
	String blockid;
	String preHash;
	int height;
	long timestamp;
	ResGetTransaction[] transactions;
	int txCount;
	String  nextHash;
	public ResGetBlockInformation(){
		this.transactions =new ResGetTransaction[]{};
	}
	@Override
	public String getEncryptionValue() {
		String str= (version)
				+ (this.blockid == null?"":this.blockid)
				+ (this.preHash== null?"":this.preHash)
				+ (this.height)
				+ (this.timestamp);
		for (int i = 0; i<this.transactions.length; i++) {
			ResGetTransaction resGetTransaction = this.transactions[i];
			str += (resGetTransaction.getEncryptionValue());
		}
		str+=txCount;
		str+=this.nextHash== null?"":this.nextHash;
		return str;
	}
}
