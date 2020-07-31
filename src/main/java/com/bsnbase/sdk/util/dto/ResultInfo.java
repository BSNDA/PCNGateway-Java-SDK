package com.bsnbase.sdk.util.dto;


/**
 * 功能描述：返回信息
 * @Created by 2018-05-27 10:06
 */
public class ResultInfo<T> {

    /**
     * 返回标识
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据信息
     */
    private T data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultInfo() {
    }

    public ResultInfo(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
