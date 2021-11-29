package com.bsnbase.sdk.entity.resp.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResHeader implements IBody {
    /**
     * Response code
     * Note: 0: Success  -1: Failure
     */
    Integer code;
    /**
     * Response message
     */
    String msg;

    @Override
    public String getEncryptionValue() {
        return (this.code == null ? "" : this.code)
                + (this.msg == null ? "" : this.msg);
    }
}
