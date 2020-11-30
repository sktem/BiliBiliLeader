package com.zero.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zero.system.entity.order.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {


    //通过关键字分页查询数据列表
        List<Order> selectPageList(@Param("order")Order order);

    //通过关键字分页查询，返回总记录数
    Integer selectPageCount(@Param("order")Order order);
    void updateState(Integer id,Integer state);
    Order selectByIdA(Integer id);

    void orderAdd(@Param("order")Order order);

    Order selectByRewardId(@Param("userId")int userId,@Param("rewardId")int rewardId);

    List<Order> selectList(@Param("uid")int uid);

    void delectAll();
}
