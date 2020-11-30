package com.zero.system.service;


import com.baomidou.mybatisplus.service.IService;
import com.zero.system.entity.channel.Channel;
import com.zero.system.util.PageBean;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/9
 */

public interface ChannelService extends IService<Channel> {
    PageBean<Channel> queryPage(Map<String, Object> paramMap);
    Channel selectByIdA(Integer id);
    void updateById(String name, Integer id,Date updateTime);
    void updateState(Integer state, Integer id);
    boolean addChannel(String name, Date createTime,Integer state);

    List<Channel> selectList();

    void delectAll();
}
