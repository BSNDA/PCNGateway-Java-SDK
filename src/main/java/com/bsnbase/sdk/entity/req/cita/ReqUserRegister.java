package com.bsnbase.sdk.entity.req.cita;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * 用户注册请求参数
 */
@Data
public class ReqUserRegister implements IBody {
	String userId ;

    @Override
    public String getEncryptionValue() {
        return this.userId == null? "":this.userId;
    }
}
