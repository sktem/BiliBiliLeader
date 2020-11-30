package com.zero.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zero.system.entity.user.User;
import com.zero.system.entity.user.UserReward;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRewardMapper extends BaseMapper<UserReward> {

    //通过关键字分页查询数据列表
    List<UserReward> selectPageList(@Param("userReward") UserReward userReward);

    //通过关键字分页查询，返回总记录数
    Integer selectPageCount(@Param("userReward") UserReward userReward);

    void userRewardAdd(UserReward userReward);

    UserReward selectByIdA(@Param("id")int id);

    void userRewardEdit(UserReward userReward);
    List<UserReward> selectList();
    void updateState(Integer id ,Integer state);

    List<UserReward> selectListBySort();

    void delectAll();


}
