package com.bsnbase.sdk.util.dto;


public class ResultInfoUtil {

    private static final int SUCCESS_CODE = 0;

    private static final int ERROR_CODE = -1;


    public static ResultInfo successResult(String message) {
        return successResult(message, null);
    }
    public static ResultInfo successResult() {
        return successResult("success", null);
    }

    public static ResultInfo successResult(Object data) {
        return successResult("success", data);
    }

    public static ResultInfo successResult(String message, Object data) {
        return createResult(SUCCESS_CODE, message, data);
    }


    public static ResultInfo errorResult(String message) {
        return errorResult(message, null);
    }

    public static ResultInfo errorResult(Object data) {
        return errorResult(null, data);
    }


    public static ResultInfo errorResult(String message, Object data) {
        return createResult(ERROR_CODE, message, data);
    }

    public static ResultInfo errorResult(int code, String message) {
        return createResult(code, message, null);
    }


    private static ResultInfo createResult(int code, String message, Object data) {
        return new ResultInfo(code+"", message, data);
    }


}
