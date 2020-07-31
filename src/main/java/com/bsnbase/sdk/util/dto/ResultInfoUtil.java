package com.bsnbase.sdk.util.dto;

/**
 * 功能描述：封装返回信息工具类
 *
 * @Created by 2018-05-27 10:17
 */
public class ResultInfoUtil {

    /**
     * 返回成功
     */
    private static final int SUCCESS_CODE = 0;

    /**
     * 错误标识
     */
    private static final int ERROR_CODE = -1;


    public static ResultInfo successResult(String message) {
        return successResult(message, null);
    }
    public static ResultInfo successResult() {
        return successResult("处理成功", null);
    }

    public static ResultInfo successResult(Object data) {
        return successResult("处理成功", data);
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
