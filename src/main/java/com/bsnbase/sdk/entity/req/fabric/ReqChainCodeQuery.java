package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of query chaincode event interface
 */
@Data
public class ReqChainCodeQuery implements IBody {
    @Override
    public String getEncryptionValue() {
        // TODO Auto-generated method stub
        return "";
    }
}
