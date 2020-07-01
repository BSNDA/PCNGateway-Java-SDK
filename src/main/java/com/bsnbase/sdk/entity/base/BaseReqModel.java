package com.bsnbase.sdk.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.common.StoreUtils;
import com.bsnbase.sdk.util.exception.GlobalException;
import com.bsnbase.sdk.util.sign.SignUtil;
import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;

@Data
public class BaseReqModel<T extends  Object&IBody> implements IBaseReqModel {

    @JSONField(name  = "header")
    ReqHeader header;

    @JSONField(name  = "mac")
    String mac ;

    @JSONField(name  = "body")
    T body;

    public void setBody(T body){
        this.body = body;
    }

    public void sign () throws Exception {

        if (this.header == null){
            throw new GlobalException("header cannot be empty");
        }

        String signValue = this.header.getHeaderString();

    	if(this.body != null) {
    		signValue += this.body.getEncryptionValue();
    	}

        byte[] by= SignUtil.sign(Common.readFile(Common.getClassPathResource(Config.config.getPrk())), signValue.getBytes());
        String sign = java.util.Base64.getEncoder().encodeToString(by);

        this.mac = sign;
    }

    @Override
    public void setReqHeader(String userCode,String appCode) {
        this.header = new ReqHeader();
        this.header.setAppCode(appCode);
        this.header.setUserCode(userCode);
    }

}
