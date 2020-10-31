package com.bsnbase.sdk.entity.req.xuperChain;


import com.google.protobuf.ByteString;
import lombok.Data;

import java.util.Map;

@Data
public class ReqInvokeRequest {

    private String moduleName;
    private String methodName;
    private String contractName;
    private Map<String, ByteString> args;
}