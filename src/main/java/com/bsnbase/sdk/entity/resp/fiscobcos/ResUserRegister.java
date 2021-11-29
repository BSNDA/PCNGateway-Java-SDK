package com.bsnbase.sdk.entity.resp.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of user registration interface
 */
@Data
public class ResUserRegister implements IBody {
    /**
     * Username
     */
    String userId;
    /**
     * User address
     */
    String userAddress;

    @Override
    public String getEncryptionValue() {
        return (this.userId == null ? "" : this.userId) + (this.userAddress == null ? "" : this.userAddress);
    }
}
