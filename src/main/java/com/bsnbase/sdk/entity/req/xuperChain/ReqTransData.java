package com.bsnbase.sdk.entity.req.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

import java.util.Map;

@Data
public class ReqTransData {
	String contractName;
    String methodName;
    String initiator;
    Map<String,String> args;
}
