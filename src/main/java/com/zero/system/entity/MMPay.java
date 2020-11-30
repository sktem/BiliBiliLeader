package com.zero.system.entity;

import lombok.Data;

@Data
public class MMPay {

    private String outTradeNo;
    private String outUserNo;
    private float price;
    private float realPrice;
    private String key;
}
