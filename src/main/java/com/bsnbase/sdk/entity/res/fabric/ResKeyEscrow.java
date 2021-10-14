package com.bsnbase.sdk.entity.res.fabric;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ResKeyEscrow implements IBody{
    BlockInfo blockInfo;
    CcRes ccRes;
   
	@Override
	public String getEncryptionValue() {




		return (this.blockInfo.getTxId() == null?"":this.blockInfo.getTxId())
				+ (this.blockInfo.getBlockHash() == null?"":this.blockInfo.getBlockHash())
				+ (this.blockInfo.getStatus() == null?"":this.blockInfo.getStatus())
				+ (this.ccRes.getCcCode() == null?"":this.ccRes.getCcCode())
				+ (this.ccRes.getCcData() == null?"":this.ccRes.getCcData());
	}
}
