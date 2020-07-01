package com.bsnbase.sdk.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.sign.SignUtil;
import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

@Data
public class BaseResModel<T extends Object&IBody> implements IBaseResModel {

    @JSONField(name  = "header")
    ResHeader header;

    @JSONField(name  = "mac")
    String mac ;

    @JSONField(name  = "body")
    T body;

    public BaseResModel(){

    }

    @Override
    public boolean verify() throws Exception {
        String signValue = (this.header==null?"":this.header.getHeaderString()) + (this.body==null?"": this.body.getEncryptionValue());

        return SignUtil.verify(Common.readFile(Common.getClassPathResource(Config.config.getPuk())), this.mac, signValue);
    }



}
