package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zero.system.entity.order.Order;
import com.zero.system.entity.user.User;
import com.zero.system.mapper.OrderMapper;
import com.zero.system.mapper.UserMapper;
import com.zero.system.service.OrderService;
import com.zero.system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //分页数据
        public List<User> selectPageList(User user){
        List<User> list=baseMapper.selectPageList(user);
        return list;

    }
    //分页数据总数
    public Integer selectPageCount(User user){
        return baseMapper.selectPageCount(user);
    }

    @Override
    public void userAdd(User user) {
        baseMapper.userAdd(user);
    }

    @Override
    public User selectByOpenId(String openId) {
        return baseMapper.selectByOpenId(openId);
    }

    @Override
    public User selectByIdA(int userId) {
        return baseMapper.selectByIdA(userId);
    }

    @Override
    public void updateByIdA(User user) {
        baseMapper.updateByIdA(user);
    }

    @Override
    public void updateBalance(User user) {
        baseMapper.updateBalance(user);
    }

    @Override
    public void delUser(Integer id) {
        baseMapper.delUser(id);
    }

    @Override
    public void updateLastTime(int id) {
        baseMapper.updateLastTime(id);
    }

    @Override
    public void delectAll() {
        baseMapper.delectAll();
    }
}
