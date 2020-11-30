package com.zero.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zero.system.entity.siteConfiguration.SiteConfiguration;
import com.zero.system.entity.wechat.WeChat;
import org.apache.ibatis.annotations.Param;

public interface WeChatMapper extends BaseMapper<WeChat> {

    WeChat selectById();

    void updateByIdA(WeChat siteConfiguration);


}
