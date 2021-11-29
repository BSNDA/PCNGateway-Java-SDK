package com.bsnbase.sdk.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.config.PublicConfig;
import com.bsnbase.sdk.util.algorithm.AlgorithmTypeContext;
import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;
import lombok.Data;

/**
 * Common response information of the receiving gateway
 */
@Data
public class BaseResModel<T extends Object & IBody> implements IBaseResModel {
    /**
     * Response header
     */
    @JSONField(name = "header")
    ResHeader header;
    /**
     * signature of the gateway
     */
    @JSONField(name = "mac")
    String mac;
    /**
     * Response body
     */
    @JSONField(name = "body")
    T body;

    public BaseResModel() {

    }

    /**
     * Gateway returns the result of signature verfication
     */
    @Override
    public boolean verify() throws Exception {
        String signValue = (this.header == null ? "" : this.header.getHeaderString()) + (this.body == null ? "" : this.body.getEncryptionValue());
        AlgorithmTypeEnum algorithmTypeEnum = AlgorithmTypeEnum.fromAlgorithmTypeEnum(Config.config.getAppInfo().getAlgorithmType());
        AlgorithmTypeContext algorithmTypeContext = new AlgorithmTypeContext(algorithmTypeEnum);
        boolean verify = algorithmTypeContext.getAlgorithmTypeHandle().verify(PublicConfig.getPublicKey(algorithmTypeEnum), this.mac, signValue);
        return verify;
    }
}
