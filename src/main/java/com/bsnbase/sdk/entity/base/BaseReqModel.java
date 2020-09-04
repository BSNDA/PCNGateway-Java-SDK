package com.bsnbase.sdk.entity.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.util.common.Common;
import com.bsnbase.sdk.util.enums.AlgorithmTypeEnum;
import com.bsnbase.sdk.util.enums.ResultInfoEnum;
import com.bsnbase.sdk.util.exception.GlobalException;
import com.bsnbase.sdk.util.algorithm.*;
import lombok.Data;

@Data
public class BaseReqModel<T extends Object & IBody> implements IBaseReqModel {
    @JSONField(name = "header")
    ReqHeader header;

    @JSONField(name = "mac")
    String mac;

    @JSONField(name = "body")
    T body;

    public BaseReqModel() {
    }

    public BaseReqModel(T body) {
        this.body = body;
    }

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
        String sign = algorithmTypeContext.getAlgorithmTypeHandle().sign(Common.readFile(Config.config.getPrk()), signValue);
        this.mac = sign;
    }


    @Override
    public void setReqHeader(String userCode,String appCode) {
        this.header = new ReqHeader();
        this.header.setAppCode(appCode);
        this.header.setUserCode(userCode);
    }
}
