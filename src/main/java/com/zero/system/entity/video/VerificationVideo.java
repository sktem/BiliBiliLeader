package com.zero.system.entity.video;

public class VerificationVideo {

    private int code;
    private String message;
    private int ttl;
    private VerificationVideoData data;

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

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public VerificationVideoData getData() {
        return data;
    }

    public void setData(VerificationVideoData data) {
        this.data = data;
    }
}
