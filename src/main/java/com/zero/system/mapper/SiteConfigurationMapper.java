package com.zero.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zero.system.entity.siteConfiguration.SiteConfiguration;
import com.zero.system.entity.withdrawal.Withdrawal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SiteConfigurationMapper extends BaseMapper<SiteConfiguration> {

    SiteConfiguration selectByIdA(@Param("id")int id);

    void updateByIdA(SiteConfiguration siteConfiguration);

}
