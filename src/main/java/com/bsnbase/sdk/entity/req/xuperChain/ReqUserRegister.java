package com.bsnbase.sdk.entity.req.xuperChain;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of user registration interface
 */
@Data
public class ReqUserRegister implements IBody {
    /**
     * User ID
     */
    @JSONField(name = "userId")
    String userId;


    @Override
    public String getEncryptionValue() {
        return (this.userId == null ? "" : this.userId);
    }
}
