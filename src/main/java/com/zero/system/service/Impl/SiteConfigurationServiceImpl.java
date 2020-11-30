package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zero.system.entity.siteConfiguration.SiteConfiguration;
import com.zero.system.entity.withdrawal.Withdrawal;
import com.zero.system.mapper.SiteConfigurationMapper;
import com.zero.system.mapper.WithdrawalMapper;
import com.zero.system.service.SiteConfigurationService;
import com.zero.system.service.WithdrawalService;
import org.springframework.stereotype.Service;

@Service
public class SiteConfigurationServiceImpl extends ServiceImpl<SiteConfigurationMapper, SiteConfiguration> implements SiteConfigurationService {
    @Override
    public SiteConfiguration selectByIdA(int id) {
        return baseMapper.selectByIdA(id);
    }

    @Override
    public void updateByIdA(SiteConfiguration siteConfiguration) {
        baseMapper.updateByIdA(siteConfiguration);
    }


}
