package com.bsnbase.sdk.entity.config;

import lombok.Data;

@Data
public class JsonConfig {
    private String nodeApi;
    private String userCode;
    private String appCode;
    private String userPrivateKey;
    private String bsnPublicKey;
    private String mspPath;
}