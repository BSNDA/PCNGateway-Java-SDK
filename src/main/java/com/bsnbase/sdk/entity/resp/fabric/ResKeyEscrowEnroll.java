package com.bsnbase.sdk.entity.resp.fabric;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Response parameters of user certificate registration in Public Key Upload Mode
 */

@Data
public class ResKeyEscrowEnroll implements IBody {

    /**
     * Certificate
     */
    @JSONField(name = "cert")
    String cert;

    @Override
    public String getEncryptionValue() {
        return (this.cert == null ? "" : this.cert);
    }
}
