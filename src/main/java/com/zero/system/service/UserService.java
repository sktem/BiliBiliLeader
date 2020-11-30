package com.zero.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zero.system.entity.order.Order;
import com.zero.system.entity.user.User;

import java.util.List;

public interface UserService extends IService<User> {

    //分页数据
    List<User> selectPageList(User user);
    //分页数据总数
    Integer selectPageCount(User user);

    void userAdd(User user);

    User selectByOpenId(String openId);

    User selectByIdA(int userId);

    void updateByIdA(User user);

    void updateBalance(User user);
    void delUser(Integer id);

    void updateLastTime(int id);

    void delectAll();
}
