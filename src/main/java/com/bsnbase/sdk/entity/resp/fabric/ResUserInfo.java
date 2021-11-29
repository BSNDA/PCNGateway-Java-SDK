package com.bsnbase.sdk.entity.resp.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of get user information interface
 */
@Data
public class ResUserInfo implements IBody {
    /**
     * Application name
     */
    String appName;
    /**
     * Application type
     */
    String appType;
    /**
     * Parameter: Key mode
     * Note: 1: hosted 2: non-hosted
     */
    Integer caType;
    /**
     * Parameter: Key type
     * Note: 1: SM2  2:ECDSA(secp256r1)
     */
    Integer algorithmType;
    /**
     * City node's MSPID
     */
    String mspId;
    /**
     * Parameter: Application chain name
     * Note: Fabric is for channelId，fisco is for groupId
     */
    String channelId;
    /**
     * cita is for version, other frameworks don't return this parameter
     */
    Integer version;

    @Override
    public String getEncryptionValue() {
        return (this.appName == null ? "" : this.appName)
                + (this.appType == null ? "" : this.appType)
                + (this.caType == null ? "" : this.caType)
                + (this.algorithmType == null ? "" : this.algorithmType)
                + (this.mspId == null ? "" : this.mspId)
                + (this.channelId == null ? "" : this.channelId)
                + (this.version == null ? "" : this.version);
    }
}
