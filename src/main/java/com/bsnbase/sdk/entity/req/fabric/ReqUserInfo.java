package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get user information interface
 */
@Data
public class ReqUserInfo implements IBody {
    /**
     * Application name
     */
    String appName;
    /**
     * Application type
     */
    String appType;
    /**
     * Key Trust type of the application
     * Note: 1: Hosted 2: Non-hosted
     */
    Integer caType;
    /**
     * Key encryption type of the application
     * Note: 1: SM2  2:ECDSA(secp256r1)
     */
    Integer algorithmType;
    /**
     * MSPID of the city node
     */
    String mspId;
    /**
     * Application chain name
     * Note: Fabric uses channelId，FISCO BCOS uses groupId
     */
    String channelId;

    @Override
    public String getEncryptionValue() {
        return null;
    }
}
