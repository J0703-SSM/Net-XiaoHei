package com.lanou.domain;

/**
 * Created by dllo on 17/11/17.
 */
public class Netease {

    private int code;

    private String msg;

    private String obj;

    public Netease() {
    }

    public Netease(int code, String msg, String obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Netease{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", obj='" + obj + '\'' +
                '}';
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

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }
}
