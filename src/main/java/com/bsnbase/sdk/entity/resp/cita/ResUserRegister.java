package com.bsnbase.sdk.entity.resp.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of user registration interface
 */
@Data
public class ResUserRegister implements IBody {
    /**
     * Username
     * Registered username
     */
    String userId;
    /**
     * User address
     */
    String userAddress;

    @Override
    public String getEncryptionValue() {
        return (userId == null ? "" : userId) + (userAddress == null ? "" : userAddress);
    }
}
