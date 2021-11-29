package com.bsnbase.sdk.entity.resp.fabric;

import lombok.Data;

/**
 * Response parameters of invoke the smart contract interface
 */
@Data
public class CcRes {
    /**
     * Chaincode status
     * Note: 200: Success 500: Failure
     */
    Integer ccCode;
    /**
     * Chaincode result
     * Note: Specific response message
     */
    String ccData;
}
