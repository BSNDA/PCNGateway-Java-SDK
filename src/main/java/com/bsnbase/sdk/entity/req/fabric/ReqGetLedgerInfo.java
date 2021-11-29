package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get the latest ledger information interface
 */
@Data
public class ReqGetLedgerInfo implements IBody {
    @Override
    public String getEncryptionValue() {
        // TODO Auto-generated method stub
        return "";
    }
}
