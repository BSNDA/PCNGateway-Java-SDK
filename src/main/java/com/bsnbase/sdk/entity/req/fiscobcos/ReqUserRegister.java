package com.bsnbase.sdk.entity.req.fiscobcos;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters for FISCO-BCOS user registration interface
 */
@Data
public class ReqUserRegister implements IBody {
    /**
     * Username
     */
    @JSONField(name = "userId")
    String userId;


    @Override
    public String getEncryptionValue() {
        return (this.userId == null ? "" : this.userId);
    }
}
