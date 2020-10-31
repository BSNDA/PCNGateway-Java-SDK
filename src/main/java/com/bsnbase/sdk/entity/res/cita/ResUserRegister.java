package com.bsnbase.sdk.entity.res.cita;

import com.alibaba.fastjson.JSON;
import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;
import org.glassfish.json.JsonUtil;

@Data
public class ResUserRegister implements IBody {
    String userId;
    String userAddress;

	@Override
	public String getEncryptionValue() {
		return (userId==null?"":userId)+(userAddress==null?"":userAddress);
	}
}
