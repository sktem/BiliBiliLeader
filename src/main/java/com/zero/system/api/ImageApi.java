package com.zero.system.api;

import com.zero.system.entity.Image;
import com.zero.system.entity.order.Order;
import com.zero.system.entity.user.User;
import com.zero.system.entity.user.UserReward;
import com.zero.system.service.ImageService;
import com.zero.system.service.OrderService;
import com.zero.system.service.UserRewardService;
import com.zero.system.service.UserService;
import com.zero.system.util.AjaxResult;
import com.zero.system.util.GenerateNum;
import com.zero.system.util.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/11
 */
@RestController
@RequestMapping("/api/image")
public class ImageApi {
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private ImageService imageService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRewardService userRewardService;
    @Autowired
    private UserService userService;
    @RequestMapping("/getImage")
    public Rest selectImg(@RequestParam("rewardId")int rewardId, @RequestParam("userId")int userId){
        try{
            Map<String,Object> map = new HashMap<>();
            UserReward userReward = userRewardService.selectByIdA(rewardId);
            Order order1 = orderService.selectByRewardId(userId,rewardId);
            User user = userService.selectByIdA(userId);
            if(null!=user){
                if(rewardId == user.getRewardId()){
                    if(order1 == null){
                        Order order = new Order();
                        order.setState(1);
                        order.setCreateTime(new Date());
                        order.setOrderNo(GenerateNum.getInstance().ShoukuanOrder());
                        order.setRewardMoney(userReward.getRewardMoney());
                        order.setRewardId(userReward.getId());
                        order.setPayMoney(userReward.getRechargeMoney());
                        order.setUserId(userId);
                        System.out.println("添加的订单信息:"+order);
                        orderService.orderAdd(order);
                    }
                    Image image=imageService.selectImage(rewardId);
                    if(null!=image){
                        map.put("image",image.getImage());
                        map.put("money",userReward.getRechargeMoney());
                        return Rest.okData(map);
                    }else{
                        return Rest.failure("暂无支付二维码!");
                    }
                }else{
                    return Rest.failure("请先完成上级奖励后重试!!");
                }

            }else{
                return Rest.failure("未知用户!");
            }


        }catch (Exception e){
            e.printStackTrace();
            return Rest.failure("系统错误!");
        }
    }
}
