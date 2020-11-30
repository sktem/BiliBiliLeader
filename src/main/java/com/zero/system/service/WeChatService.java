package com.zero.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zero.system.entity.siteConfiguration.SiteConfiguration;
import com.zero.system.entity.wechat.WeChat;

public interface WeChatService extends IService<WeChat> {

    WeChat selectById();

    void updateByIdA(WeChat siteConfiguration);

}
