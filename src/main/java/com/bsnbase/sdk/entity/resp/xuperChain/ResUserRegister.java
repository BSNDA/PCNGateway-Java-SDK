package com.bsnbase.sdk.entity.resp.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of user registration interface
 */
@Data
public class ResUserRegister implements IBody {
    /**
     * User ID
     */
    String userId;
    /**
     * User address
     */
    String userAddr;

    @Override
    public String getEncryptionValue() {
        return (this.userId == null ? "" : this.userId) + (this.userAddr == null ? "" : this.userAddr);
    }
}
