package com.bsnbase.sdk.util.common;

import lombok.Data;

import java.security.PrivateKey;

@Data
public class UserCertInfo {
    /**
     * User certificate PKCS10
     */
    String CSRPem;
    /**
     * User private key
     */
    PrivateKey key;
}
