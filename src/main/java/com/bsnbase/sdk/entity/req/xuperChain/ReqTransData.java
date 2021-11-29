package com.bsnbase.sdk.entity.req.xuperChain;

import lombok.Data;

import java.util.Map;

/**
 * Request parameters of invoke the smart contract in Public Key Upload Mode interface
 */
@Data
public class ReqTransData {
    /**
     * Contract name
     */
    String contractName;
    /**
     * Method name
     */
    String methodName;
    /**
     * xupchain's account address
     */
    String initiator;
    /**
     * Contract parameters
     */
    Map<String, byte[]> args;
}
