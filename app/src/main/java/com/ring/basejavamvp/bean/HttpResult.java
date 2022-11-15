package com.ring.basejavamvp.bean;

import java.io.Serializable;

public class HttpResult<T> implements Serializable {
    private int status;
    private String msg;
    private T info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
