package com.bsnbase.sdk.util.exception;


import com.bsnbase.sdk.util.enums.ResultInfoEnum;

/**
 * Function description: Common exception
 *
 * @Created by 2018-06-12 12:49
 */
public class GlobalException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int code;

    public GlobalException(ResultInfoEnum resultInfoEnum) {
        super(resultInfoEnum.getMsg());
        this.code = resultInfoEnum.getCode();
    }

    public GlobalException(String message) {
        super(message);
        this.code = -1;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
