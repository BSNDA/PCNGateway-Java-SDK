package com.bsnbase.sdk.entity.res;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ResChainCodeQuery implements IBody{
    String eventId;
    String eventKey;
    String notifyUrl;
    String attachArgs;
    String createTime;
    String orgCode;
    String userCode;
    String appCode;
    String chainCode;
	@Override
	public String getEncryptionValue() {
		return (this.eventId == null?"":this.eventId)
			+(this.eventKey == null?"":this.eventKey)
			+(this.notifyUrl == null?"":this.notifyUrl)
			+(this.attachArgs == null?"":this.attachArgs)
			+(this.createTime == null?"":this.createTime)
			+(this.orgCode == null?"":this.orgCode)
			+(this.userCode == null?"":this.userCode)
			+(this.appCode == null?"":this.appCode)
			+(this.chainCode == null?"":this.chainCode);
	}
    
}
