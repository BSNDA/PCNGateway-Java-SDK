package com.bsnbase.sdk.entity.req.fabric;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of user registration interface
 */
@Data
public class ReqUserRegister implements IBody {
    /**
     * Username
     * Note: the length is less thatn 20
     */
    @JSONField(name = "name")
    String name;

    /**
     * User password
     * Note: The passwrod can be null in  Key Trust Mode; if it is null in Public Key Upload Mode, a nonce will be returned
     */
    @JSONField(name = "secret")
    String secret;

    /**
     * Extended attributes
     * Note: the extended attributes of the user, should be written in json format. For example: {“ley1”:”123”,”key2”:”456”}
     */
    @JSONField(name = "extendProperties")
    String extendProperties;

    @Override
    public String getEncryptionValue() {
        return (this.name == null ? "" : this.name) + (this.secret == null ? "" : this.secret) + (this.extendProperties == null ? "" : this.extendProperties);
    }
}
