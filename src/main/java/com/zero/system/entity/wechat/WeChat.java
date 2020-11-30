package com.zero.system.entity.wechat;

import lombok.Data;

@Data
public class WeChat {

    private String appId;
    private String secret;
    private String domainName; //授权域名

}
