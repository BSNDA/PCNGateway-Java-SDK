package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ReqChainCodeRegister implements IBody {
    /**
     * 事件类型
     * 1：出块事件
     * 2：合约事件
     */
    int eventType;
    /**
     * 合约地址
     * EventType为1时可以为空，为2时，不可与contractName同时为空
     */
    String contractAddress;
    /**
     * 合约名
     * EventType为1时可以为空，为2时，不可与contractAddress同时为空
     */
    String contractName;
    /**
     * 通知地址
     */
    String notifyUrl;
    /**
     * 附件参数
     */
    String attachArgs;

    @Override
    public String getEncryptionValue() {
        return (this.eventType == 0 ? "" : this.eventType)
                + (this.contractAddress == null ? "" : this.contractAddress)
                + (this.contractName == null ? "" : this.contractName)
                + (this.notifyUrl == null ? "" : this.notifyUrl)
                + (this.attachArgs == null ? "" : this.attachArgs);
    }
}
