package com.bsnbase.sdk.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.config.PublicConfig;
import com.bsnbase.sdk.util.algorithm.AlgorithmTypeContext;
import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * General data structure of the receiving gateway
 */
@Data
public class BaseResArrayModel<T extends Object & IBody> implements IBaseResModel {

    @JSONField(name = "header")
    ResHeader header;

    @JSONField(name = "mac")
    String mac;

    @JSONField(name = "body")
    List<T> body;

    @Override
    public boolean verify() throws Exception {
        String signValue = (this.header == null ? "" : this.header.getHeaderString());

        for (int i = 0; i < this.body.size(); i++) {
            signValue += this.body.get(i).getEncryptionValue();
        }
        AlgorithmTypeEnum algorithmTypeEnum = AlgorithmTypeEnum.fromAlgorithmTypeEnum(Config.config.getAppInfo().getAlgorithmType());
        AlgorithmTypeContext algorithmTypeContext = new AlgorithmTypeContext(algorithmTypeEnum);
        boolean verify = algorithmTypeContext.getAlgorithmTypeHandle().verify(PublicConfig.getPublicKey(algorithmTypeEnum), this.mac, signValue);
        return verify;
    }

}
