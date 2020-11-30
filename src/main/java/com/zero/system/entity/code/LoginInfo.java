package com.zero.system.entity.code;

public class LoginInfo {

    private int code;
    private String message;
    private int ts;
    private boolean status;
    private LoginInfoData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LoginInfoData getData() {
        return data;
    }

    public void setData(LoginInfoData data) {
        this.data = data;
    }
}
