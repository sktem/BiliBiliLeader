package com.zero.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zero.system.entity.order.Order;
import com.zero.system.entity.siteConfiguration.SiteConfiguration;
import com.zero.system.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    //通过关键字分页查询数据列表
        List<User> selectPageList(@Param("user") User user);

    //通过关键字分页查询，返回总记录数
    Integer selectPageCount(@Param("user") User order);
   void  updateRid(@Param("rId")Integer rId ,@Param("id") Integer id,@Param("balance") String balance);
   User selectByIdA(@Param("id")int id);

    void userAdd(User user);

    User selectByOpenId(@Param("id")String openId);



    void updateByIdA(User user);

    void updateBalance(User user);
    void     delUser(@Param("id") Integer id);

    void updateLastTime(@Param("id")int id);


    void delectAll();
}
