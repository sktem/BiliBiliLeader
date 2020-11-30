package com.zero.system.entity.code;

public class Url {

    private int code;
    private boolean status;
    private int ts;
    private UrlData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }

    public UrlData getData() {
        return data;
    }

    public void setData(UrlData data) {
        this.data = data;
    }
}
