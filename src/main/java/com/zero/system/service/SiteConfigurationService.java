package com.zero.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zero.system.entity.siteConfiguration.SiteConfiguration;
import com.zero.system.entity.withdrawal.Withdrawal;

public interface SiteConfigurationService extends IService<SiteConfiguration> {

    SiteConfiguration selectByIdA(int id);

    void updateByIdA(SiteConfiguration siteConfiguration);

}
