package com.bsnbase.sdk.util.enums;

/**
 * 功能描述：返回信息
 *
 * @Created by 2018-06-12 12:44
 */
public enum ResultInfoEnum {
    SUCCESS(0, "成功"),

    SYSTEM_ERROR(-1, "系统异常，稍后重试"),

    RES_MAC_ERROR(-1, "验签失败"),

    INVALID_RESPONSE_ERROR(-1, "响应结果无效"),

    DATA_CONVERSION_ERROR(-1, "数据转换失败,无效的响应结果"),

    GET_CSR_ERROR(-1, "获取CSR异常"),

    REQUEST_HEADER_ERROR(-1, "请求参数的header不能为空"),

    TRANSACTION_SIGNING_ERROR(-1, "交易签名失败"),

    USER_CERTIFICATE_ERROR(-1, "获取用户证书失败"),

    TRANSACTION_CONVERSION_ERROR(-1, "交易数据转换失败"),

    GET_APP_INFO_ERROR(-1, "获取App信息异常"),

    ALGORITHM_TYPE_ERROR(-1, "算法类型无效"),

    BLOCK_HEIGHT_ERROR(-1, "获取块高失败");


    ResultInfoEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 标识
     */
    private int code;

    /**
     * 信息
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
