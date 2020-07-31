package com.bsnbase.sdk.entity.res.xuperChain;

import lombok.Data;

@Data
public class ContractRequestData {
    String contractName;
    String methodName;
    String args;
}