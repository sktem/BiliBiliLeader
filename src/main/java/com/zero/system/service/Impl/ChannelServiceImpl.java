package com.zero.system.service.Impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zero.system.entity.channel.Channel;
import com.zero.system.mapper.ChannelMapper;
import com.zero.system.service.ChannelService;
import com.zero.system.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/9
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper,Channel> implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;
    @Override
    public PageBean<Channel> queryPage(Map<String, Object> paramMap) {
        PageBean<Channel> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Channel> datas = channelMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = channelMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public Channel selectByIdA(Integer id) {
        return channelMapper.selectByIdA(id);
    }

    @Override
    public void updateById(String name, Integer id,Date updateTime) {
        channelMapper.updateById(name,id,updateTime);
    }

    @Override
    public void updateState(Integer state, Integer id) {
        channelMapper.updateState(state,id);
    }

    @Override
    public boolean addChannel(String name, Date createTime,Integer state) {
        return channelMapper.addChannel(name,createTime,state)>0;
    }

    @Override
    public List<Channel> selectList() {
        return channelMapper.selectList();
    }

    @Override
    public void delectAll() {
        baseMapper.delectAll();
    }
}


