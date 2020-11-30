package com.zero.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zero.system.entity.user.User;
import com.zero.system.entity.user.UserReward;

import java.util.List;

public interface UserRewardService extends IService<UserReward> {

    //分页数据
    List<UserReward> selectPageList(UserReward userReward);
    //分页数据总数
    Integer selectPageCount(UserReward userReward);

    void userRewardAdd(UserReward userReward);

    UserReward selectByIdA(int id);

    void userRewardEdit(UserReward userReward);
    List<UserReward> selectList();
    void updateState(Integer id,Integer state );
    List<UserReward> selectListBySort();

    void delectAll();

}
