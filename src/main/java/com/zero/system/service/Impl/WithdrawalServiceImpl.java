package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zero.system.entity.order.Order;
import com.zero.system.entity.withdrawal.Withdrawal;
import com.zero.system.mapper.OrderMapper;
import com.zero.system.mapper.WithdrawalMapper;
import com.zero.system.service.OrderService;
import com.zero.system.service.WithdrawalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawalServiceImpl extends ServiceImpl<WithdrawalMapper, Withdrawal> implements WithdrawalService {

    //分页数据
        public List<Withdrawal> selectPageList(Withdrawal withdrawal){
        List<Withdrawal> list=baseMapper.selectPageList(withdrawal);
        return list;

    }
    //分页数据总数
    public Integer selectPageCount(Withdrawal withdrawal){
        return baseMapper.selectPageCount(withdrawal);
    }

    @Override
    public void withdrawalAdd(Withdrawal withdrawal) {
        baseMapper.withdrawalAdd(withdrawal);
    }

    @Override
    public void delectAll() {
        baseMapper.delectAll();
    }


}
