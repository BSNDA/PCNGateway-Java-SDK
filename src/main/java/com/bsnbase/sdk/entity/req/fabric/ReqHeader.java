package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Parameters of request header
 */
@Data
public class ReqHeader implements IBody {
    /**
     * Usercode
     */
    String userCode;
    /**
     * Appcode
     */
    String appCode;
    // String tId;

    @Override
    public String getEncryptionValue() {
        return (this.userCode == null ? "" : this.userCode) + (this.appCode == null ? "" : this.appCode);
    }

};
