package com.zero.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zero.system.entity.Admin;
import com.zero.system.entity.log.BilibiliLog;
import com.zero.system.entity.order.Order;
import com.zero.system.util.PageBean;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

/**
 * @Classname BilibiliLogService
 * @Description None
 * @Date 2019/7/16 12:20
 * @Created by WDD
 */
public interface BilibiliLogService extends IService<BilibiliLog> {

    void BilibiliLogAdd(BilibiliLog log);

    BilibiliLog selectByAid(int aid);

    List<BilibiliLog> selectByAidList(int aid);

}
