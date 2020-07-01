package com.bsnbase.sdk.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ReqHeader {
    @JSONField(name  = "userCode")
    String userCode ;
    @JSONField(name = "appCode")
    String appCode ;

    public String getHeaderString(){
        return this.userCode + this.appCode;
    }
}



