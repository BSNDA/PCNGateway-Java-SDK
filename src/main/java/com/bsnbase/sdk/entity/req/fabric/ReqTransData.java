package com.bsnbase.sdk.entity.req.fabric;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Request parameters of get transaction data interface
 */

@Data
public class ReqTransData implements IBody {
    /**
     * Transaction ID
     */
    String txId;
    /**
     * DataType Options as json
     */
    String dataType ;

    @Override
    public String getEncryptionValue() {

        return (this.txId == null ? "" : this.txId)
                + (this.dataType == null ? "" : this.dataType);
    }
}
