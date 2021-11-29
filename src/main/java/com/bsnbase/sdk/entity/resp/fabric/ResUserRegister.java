package com.bsnbase.sdk.entity.resp.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of user registration interface
 */
@Data
public class ResUserRegister implements IBody {
    /**
     * Username
     * Note: The length should be less than 20
     */
    String name;
    /**
     * Password
     * Note: A random password will be returned if the request parameter password is null in Public Key Upload Mode
     */
    String secret;

    @Override
    public String getEncryptionValue() {
        return (this.name == null ? "" : this.name) + (this.secret == null ? "" : this.secret);
    }
}
