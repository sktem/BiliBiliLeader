package com.zero.system.controller.order;


import com.zero.system.entity.order.Order;
import com.zero.system.entity.wechat.WeChat;
import com.zero.system.service.OrderService;
import com.zero.system.util.AjaxResult;
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
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
private AjaxResult ajaxResult;
    @RequestMapping("/list")
    public String list(WeChat weChat, Model model){

        return "manager/order/order";
    }

    @RequestMapping("/orderBackContent")
    @ResponseBody
    public ResultMap<List<Order>> backContent(@RequestParam("page") int page, @RequestParam(value = "state",required = false)Integer state,
                                              @RequestParam("limit") int limit,
                                              HttpServletRequest req){
        System.out.println("backContent========================"+limit);
        Order order = new Order();
        order.setRows(limit);
        if(null == state){
            order.setState(0);
        }else
        {
            order.setState(state);
        }

        System.out.println("page:"+order.toString());
        if(page==1){
            order.setStart(0);
        }else{
            order.setStart((limit*page-10));
        }
        List<Order> orderList=orderService.selectPageList(order);
        int totals=orderService.selectPageCount(order);
        order.setTotalRecord(totals);


        return new ResultMap<List<Order>>("",orderList,0,totals);
    }
    @RequestMapping("/updateState")
    @ResponseBody
    public AjaxResult updateState(@RequestParam("id")Integer id){
    try{
        orderService.updateState(id);
        ajaxResult.ajaxTrue("确认收款成功!");
    }catch (Exception e){
        e.printStackTrace();
        ajaxResult.ajaxFalse("系统错误");
    }
        return ajaxResult;
    }
    @RequestMapping("/state")
    @ResponseBody
    public  AjaxResult state(@RequestParam("id")Integer id,@RequestParam("state")Integer state){

        try{
//            imageService.updateState(id,state);
            ajaxResult.ajaxTrue("成功");
        }catch(Exception e){
            ajaxResult.ajaxFalse("失败");
            e.printStackTrace();
        }
        return ajaxResult;
    }
}
