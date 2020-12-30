package com.bsnbase.sdk.entity.res.cita;

import lombok.Data;

@Data
public class BlockEvent {
    /**
     *出块事件
     */
    String eventId;

    /**
     *应用编号
     */
    String appcode;
    /**
     *用户编号
     */
    String userCode;
    /**
     *通知地址
     */
    String notifyUrl;
    /**
     *附件参数
     */
    String attachArgs;
    /**
     *创建时间
     */
    String createTime;


}