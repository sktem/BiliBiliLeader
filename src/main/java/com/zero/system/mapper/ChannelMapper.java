package com.zero.system.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zero.system.entity.channel.Channel;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/9
 */
public interface ChannelMapper extends BaseMapper<Channel> {
    List<Channel> queryList(Map<String, Object> paramMap);
    Integer queryCount(Map<String, Object> paramMap);
    Channel selectByIdA(Integer id);
    void updateById(@Param("name") String name, @Param("id") Integer id,@Param("updateTime")Date updateTime);
    void updateState(@Param("state") Integer state, @Param("id") Integer id);
    Integer addChannel(@Param("name") String name, @Param("createTime") Date createTime,@Param("state") Integer state);

    List<Channel> selectList();

    void delectAll();
}
