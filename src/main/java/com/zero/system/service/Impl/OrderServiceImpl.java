package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zero.system.entity.order.Order;
import com.zero.system.entity.user.User;
import com.zero.system.entity.user.UserReward;
import com.zero.system.mapper.OrderMapper;
import com.zero.system.mapper.UserMapper;
import com.zero.system.service.OrderService;
import com.zero.system.service.UserRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private UserRewardService userRewardService;
    @Autowired
    private UserMapper userMapper;
    //分页数据
        public List<Order> selectPageList(Order order){
        List<Order> list=baseMapper.selectPageList(order);
        return list;

    }
    //分页数据总数
    public Integer selectPageCount(Order order){
        return baseMapper.selectPageCount(order);
    }

    @Override
    public void updateState(Integer id) {
            Order order =baseMapper.selectByIdA(id);
            List<UserReward>list=userRewardService.selectList();
            for (int i=0;i<list.size();i++){
                if(list.get(i).getId()==order.getRewardId()){
                    if(i!=(list.size()-1)){
                        order.setRewardId(list.get(i+1).getId());
                        User user=userMapper.selectByIdA(order.getUserId());
                        Double money=Double.parseDouble(user.getBalance());
                        money=money+order.getRewardMoney();
                        userMapper.updateRid(list.get(i+1).getId(),order.getUserId(),money+"");
                        break;
                    }else{
                        order.setRewardId(list.get(i).getId());
                        User user=userMapper.selectByIdA(order.getUserId());
                        Double money=Double.parseDouble(user.getBalance());
                        money=money+order.getRewardMoney();
                        userMapper.updateRid(list.get(i).getId(),order.getUserId(),money+"");
                        break;
                    }

                }
            }
            baseMapper.updateState(id,2);

    }

    @Override
    public void orderAdd(Order order) {
        baseMapper.orderAdd(order);
    }

    @Override
    public Order selectByRewardId(int userId,int rewardId) {
        return baseMapper.selectByRewardId(userId,rewardId);
    }

    @Override
    public List<Order> selectList(int uid) {
        return baseMapper.selectList(uid);
    }

    @Override
    public void delectAll() {
        baseMapper.delectAll();
    }
}
