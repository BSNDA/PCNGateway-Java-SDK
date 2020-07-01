package com.bsnbase.sdk.util.enums;


public enum ResultInfoEnum {
    SUCCESS(0, "success"),
    SYSTEM_ERROR(-1, "system error"),
    RES_MAC_ERROR(-1, "sign error");


    ResultInfoEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

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
