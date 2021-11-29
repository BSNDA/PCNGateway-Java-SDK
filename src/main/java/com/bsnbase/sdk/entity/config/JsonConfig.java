package com.bsnbase.sdk.entity.config;

import lombok.Data;

/**
 * configure the information entity by json format
 */
@Data
public class JsonConfig {
    /**
     * Node gateway Url
     */
    private String nodeApi;
    /**
     * Usercode
     */
    private String userCode;
    /**
     * Appcode
     */
    private String appCode;
    /**
     * User's private key
     */
    private String userPrivateKey;
    /**
     * BSN gateway public key
     */
    private String bsnPublicKey;
    /**
     * Certificate storage directory
     */
    private String mspPath;
}