package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zero.system.entity.user.User;
import com.zero.system.entity.user.UserReward;
import com.zero.system.mapper.UserMapper;
import com.zero.system.mapper.UserRewardMapper;
import com.zero.system.service.UserRewardService;
import com.zero.system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRewardServiceImpl extends ServiceImpl<UserRewardMapper, UserReward> implements UserRewardService {

    //分页数据
        public List<UserReward> selectPageList(UserReward userReward){
        List<UserReward> list=baseMapper.selectPageList(userReward);
        return list;

    }
    //分页数据总数
    public Integer selectPageCount(UserReward userReward){
        return baseMapper.selectPageCount(userReward);
    }

    @Override
    public void userRewardAdd(UserReward userReward) {
        baseMapper.userRewardAdd(userReward);
    }

    @Override
    public UserReward selectByIdA(int id) {
        return baseMapper.selectByIdA(id);
    }

    @Override
    public void userRewardEdit(UserReward userReward) {
        baseMapper.userRewardEdit(userReward);
    }

    @Override
    public List<UserReward> selectList() {

        return baseMapper.selectList();
    }

    @Override
    public void updateState(Integer id,Integer state ) {
      baseMapper.updateState(id,state);
    }

    @Override
    public List<UserReward> selectListBySort() {
        return baseMapper.selectListBySort();
    }

    @Override
    public void delectAll() {
        baseMapper.delectAll();
    }
}
