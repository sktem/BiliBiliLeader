package com.zero.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zero.system.entity.order.Order;

import java.util.List;

public interface OrderService extends IService<Order> {

    //分页数据
    List<Order> selectPageList(Order order);
    //分页数据总数
    Integer selectPageCount(Order order);
    void updateState(Integer id);

    void orderAdd(Order order);

    Order selectByRewardId(int userId,int rewardId);

    List<Order> selectList(int uid);

    void delectAll();

}
