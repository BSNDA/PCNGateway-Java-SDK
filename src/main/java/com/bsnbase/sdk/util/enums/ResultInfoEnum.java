package com.bsnbase.sdk.util.enums;

/**
 * Function description: returned information
 */
public enum ResultInfoEnum {

    SUCCESS(0, "Success"),

    SYSTEM_ERROR(-1, "System exception, try again later"),

    RES_MAC_ERROR(-1, "Signature verification failed"),

    INVALID_RESPONSE_ERROR(-1, "Response result is invalid"),

    DATA_CONVERSION_ERROR(-1, "Data conversion failed, invalid response result"),

    GET_CSR_ERROR(-1, "Get CSR exception"),

    REQUEST_HEADER_ERROR(-1, "The header of the request parameter cannot be empty"),

    TRANSACTION_SIGNING_ERROR(-1, "Transaction signature failed"),

    USER_CERTIFICATE_ERROR(-1, "Failed to get user certificate"),

    TRANSACTION_CONVERSION_ERROR(-1, "Failed to convert transaction data"),

    GET_APP_INFO_ERROR(-1, "Exception to get App information"),

    ALGORITHM_TYPE_ERROR(-1, "Invalid algorithm type"),

    BLOCK_HEIGHT_ERROR(-1, "Failed to get block height"),

    FUNCTION_ERROR(-1, "The method is not supported by the contract"),

    FUNCTION_CALL_ERROR(-1, "Method call error"),

    CONFIG_NOT_EXISTS(-1, "Configuration file does not exist");


    /**
     * Code
     */
    private int code;
    /**
     * Message
     */
    private String msg;

    ResultInfoEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
