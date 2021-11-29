package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of user registration interface
 */
@Data
public class ReqUserRegister implements IBody {
    /**
     * Username
     * Registered username, 6-20 numbers or letters, cannot contain Chinese characters, special characters, etc.
     */
    String userId;

    @Override
    public String getEncryptionValue() {
        return this.userId == null ? "" : this.userId;
    }
}
