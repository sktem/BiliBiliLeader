package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zero.system.entity.Admin;
import com.zero.system.entity.log.BilibiliLog;
import com.zero.system.entity.order.Order;
import com.zero.system.mapper.AdminMapper;
import com.zero.system.mapper.BilibiliLogMapper;
import com.zero.system.mapper.OrderMapper;
import com.zero.system.service.AdminService;
import com.zero.system.service.BilibiliLogService;
import com.zero.system.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname BilibiliLogServiceImpl
 * @Description None
 * @Date 2019/7/16 12:20
 * @Created by WDD
 */
@Service
public class BilibiliLogServiceImpl  extends ServiceImpl<BilibiliLogMapper, BilibiliLog> implements BilibiliLogService {

    @Autowired
    private BilibiliLogMapper bilibiliLogMapper;


    @Override
    public void BilibiliLogAdd(BilibiliLog log) {
        bilibiliLogMapper.BilibiliLogAdd(log);
    }

    @Override
    public BilibiliLog selectByAid(int aid) {
        return bilibiliLogMapper.selectByAid(aid);
    }

    @Override
    public List<BilibiliLog> selectByAidList(int aid) {
        return bilibiliLogMapper.selectByAidList(aid);
    }
}
