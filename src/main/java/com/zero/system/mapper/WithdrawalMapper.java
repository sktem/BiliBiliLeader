package com.zero.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zero.system.entity.order.Order;
import com.zero.system.entity.withdrawal.Withdrawal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WithdrawalMapper extends BaseMapper<Withdrawal> {

    //通过关键字分页查询数据列表
        List<Withdrawal> selectPageList(@Param("withdrawal") Withdrawal withdrawal);

    //通过关键字分页查询，返回总记录数
    Integer selectPageCount(@Param("withdrawal") Withdrawal withdrawal);

    void withdrawalAdd(@Param("withdrawal") Withdrawal withdrawal);

    void delectAll();
}
