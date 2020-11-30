package com.zero.system.controller.withdrawal;


import com.zero.system.entity.order.Order;
import com.zero.system.entity.wechat.WeChat;
import com.zero.system.entity.withdrawal.Withdrawal;
import com.zero.system.service.WithdrawalService;
import com.zero.system.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/with")
public class WithdrawalController {


    @Autowired
    private WithdrawalService withdrawalService;

    @RequestMapping("/list")
    public String update(WeChat weChat, Model model){

        return "manager/withdrawal/withdrawal";
    }

    @RequestMapping("/withdrawalBackContent")
    @ResponseBody
    public ResultMap<List<Withdrawal>> backContent(@RequestParam("page") int page, @RequestParam(value = "state",required = false)Integer state,
                                                   @RequestParam(value = "userId",required = false)Integer userId,@RequestParam("limit") int limit,
                                                   HttpServletRequest req){
        System.out.println("backContent========================"+limit);
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setRows(limit);
        if(null == state){
            withdrawal.setState(0);
        }else
        {
            withdrawal.setState(state);
        }
        if(null == userId){
            withdrawal.setUserId(0);
        }else
        {
            withdrawal.setUserId(userId);
        }


        System.out.println("page:"+withdrawal.toString());
        if(page==1){
            withdrawal.setStart(0);
        }else{
            withdrawal.setStart((limit*page-10));
        }
        List<Withdrawal> orderList=withdrawalService.selectPageList(withdrawal);
        int totals=withdrawalService.selectPageCount(withdrawal);
        withdrawal.setTotalRecord(totals);


        return new ResultMap<List<Withdrawal>>("",orderList,0,totals);
    }

}
