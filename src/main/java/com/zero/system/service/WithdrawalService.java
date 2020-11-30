package com.zero.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zero.system.entity.order.Order;
import com.zero.system.entity.withdrawal.Withdrawal;

import java.util.List;

public interface WithdrawalService extends IService<Withdrawal> {

    //分页数据
    List<Withdrawal> selectPageList(Withdrawal order);
    //分页数据总数
    Integer selectPageCount(Withdrawal order);

    void withdrawalAdd( Withdrawal withdrawal);

    void delectAll();
}
