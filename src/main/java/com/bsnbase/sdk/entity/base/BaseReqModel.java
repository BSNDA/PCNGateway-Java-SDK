package com.bsnbase.sdk.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.util.algorithm.AlgorithmTypeContext;
import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import lombok.Data;

/**
 * General data structure of the request gateway
 */
@Data
public class BaseReqModel<T extends Object & IBody> implements IBaseReqModel {
    /**
     * Request header
     */
    @JSONField(name = "header")
    ReqHeader header;
    /**
     * Request signature, where the content of the signature is "header information + the value retrieved by getEncryptionValue method.
     */
    @JSONField(name = "mac")
    String mac;
    /**
     * Request body
     */
    @JSONField(name = "body")
    T body;

    public BaseReqModel() {
    }

    public BaseReqModel(T body) {
        this.body = body;
    }

    /**
     * The request gateway calls the "sign" method
     */
    @Override
    public void sign() throws Exception {
        if (this.header == null) {
            throw new GlobalException(ResultInfoEnum.REQUEST_HEADER_ERROR);
        }

        String signValue = this.header.getHeaderString();

        if (this.body != null) {
            signValue += this.body.getEncryptionValue();
        }
        AlgorithmTypeEnum algorithmTypeEnum = AlgorithmTypeEnum.fromAlgorithmTypeEnum(Config.config.getAppInfo().getAlgorithmType());
        AlgorithmTypeContext algorithmTypeContext = new AlgorithmTypeContext(algorithmTypeEnum);
        String sign = algorithmTypeContext.getAlgorithmTypeHandle().sign(Config.config.getPrk(), signValue);
        this.mac = sign;
    }


    @Override
    public void setReqHeader(String userCode, String appCode) {
        this.header = new ReqHeader();
        this.header.setAppCode(appCode);
        this.header.setUserCode(userCode);
    }
}
