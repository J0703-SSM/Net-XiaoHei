package com.lanou.util;

/**
 * 封装信息
 */
public class ResultMsg<T> {

    private int resultCode;

    private String message;

    private T t;

    public ResultMsg() {
    }

    public ResultMsg(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public ResultMsg(int resultCode, String message, T t) {
        this.resultCode = resultCode;
        this.message = message;
        this.t = t;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
