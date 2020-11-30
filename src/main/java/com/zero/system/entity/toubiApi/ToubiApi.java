package com.zero.system.entity.toubiApi;


public class ToubiApi {

    private int code;
    private String message;
    private ToubiData data;
    private String ttl;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public ToubiData getData() {
        return data;
    }

    public void setData(ToubiData toubiData) {
        this.data = toubiData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
}
