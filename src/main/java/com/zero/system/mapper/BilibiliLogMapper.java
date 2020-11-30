package com.zero.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zero.system.entity.Admin;
import com.zero.system.entity.log.BilibiliLog;
import com.zero.system.entity.order.Order;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Classname AdminMapper
 * @Description None
 * @Date 2019/7/16 12:21
 * @Created by WDD
 */
public interface BilibiliLogMapper extends BaseMapper<BilibiliLog> {

    void BilibiliLogAdd(BilibiliLog log);

    BilibiliLog selectByAid(int aid);

    List<BilibiliLog> selectByAidList(int aid);


}
