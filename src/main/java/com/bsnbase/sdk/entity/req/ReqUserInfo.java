package com.bsnbase.sdk.entity.req;

import com.bsnbase.sdk.entity.base.IBody;

import lombok.Data;

@Data
public class ReqUserInfo implements IBody  {
    String appName;
    String appType;
    Integer caType;
    Integer algorithmType;
    String mspId;
    String channelId;
	@Override
	public String getEncryptionValue() {
		return null;
	}
}
