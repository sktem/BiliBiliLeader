package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zero.system.entity.siteConfiguration.SiteConfiguration;
import com.zero.system.entity.wechat.WeChat;
import com.zero.system.mapper.SiteConfigurationMapper;
import com.zero.system.mapper.WeChatMapper;
import com.zero.system.service.SiteConfigurationService;
import com.zero.system.service.WeChatService;
import org.springframework.stereotype.Service;

@Service
public class WeChatServiceImpl extends ServiceImpl<WeChatMapper, WeChat> implements WeChatService {
    @Override
    public WeChat selectById() {
        return baseMapper.selectById();
    }

    @Override
    public void updateByIdA(WeChat weChat) {
        baseMapper.updateByIdA(weChat);
    }


}
