package com.bsnbase.sdk.entity.resp.xuperChain;

import lombok.Data;

/**
 * Response parameters of get transaction information interface
 */
@Data
public class ContractRequestData {
    /**
     * Contact name
     */
    String contractName;
    /**
     * Method name
     */
    String methodName;
    /**
     * Parameters
     */
    String args;
}