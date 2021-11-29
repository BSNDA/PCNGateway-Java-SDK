package com.bsnbase.sdk.entity.req.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters for FISCO-BCOS chaincode event query interface
 */
@Data
public class ReqChainCodeQuery implements IBody {
    @Override
    public String getEncryptionValue() {
        // TODO Auto-generated method stub
        return "";
    }
}
