package com.zero.system.controller.user;


import com.zero.system.entity.order.Order;
import com.zero.system.entity.user.User;
import com.zero.system.entity.user.UserReward;
import com.zero.system.entity.wechat.WeChat;
import com.zero.system.service.OrderService;
import com.zero.system.service.UserRewardService;
import com.zero.system.service.UserService;
import com.zero.system.util.AjaxResult;
import com.zero.system.util.Rest;
import com.zero.system.util.ResultMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRewardService UserRewardService;
    @Autowired
    private AjaxResult ajaxResult;

    @RequestMapping("/list")
    public String list( Model model){

        return "manager/user/user";
    }

    @RequestMapping("/userBackContent")
    @ResponseBody
    public ResultMap<List<User>> backContent(@RequestParam("page") int page, @RequestParam(value = "userName",required = false)String userName,
                                             @RequestParam(value = "invitationCode",required = false)String invitationCode,@RequestParam("limit") int limit,
                                              HttpServletRequest req){
        System.out.println("backContent========================"+limit);
        User user = new User();
        user.setRows(limit);
        if(null != userName){
            user.setUserName(userName);
        }
        if(null != invitationCode){
            user.setInvitationCode(invitationCode);
        }


        System.out.println("page:"+user.toString());
        if(page==1){
            user.setStart(0);
        }else{
            user.setStart((limit*page-10));
        }
        List<User> orderList=userService.selectPageList(user);
        int totals=userService.selectPageCount(user);
        user.setTotalRecord(totals);


        return new ResultMap<List<User>>("",orderList,0,totals);
    }

    @RequestMapping("/listUserReward")
    public String listUserReward( Model model){

        return "manager/user/userReward";
    }

    @RequestMapping("/userRewardBackContent")
    @ResponseBody
    public ResultMap<List<UserReward>> userRewardBackContent(@RequestParam("page") int page, @RequestParam(value = "name",required = false)String name,
                                                             @RequestParam("limit") int limit,
                                                             HttpServletRequest req){
        System.out.println("backContent========================"+limit);
        UserReward userReward = new UserReward();
        userReward.setRows(limit);
        if(null != name || "".equals(name)){
            userReward.setName(name.trim());
        }

        System.out.println("page:"+userReward.toString());
        if(page==1){
            userReward.setStart(0);
        }else{
            userReward.setStart((limit*page-10));
        }
        List<UserReward> orderList=UserRewardService.selectPageList(userReward);
        int totals=UserRewardService.selectPageCount(userReward);
        userReward.setTotalRecord(totals);


        return new ResultMap<List<UserReward>>("",orderList,0,totals);
    }

    @RequestMapping("/UserRewardAdd")
    public String UserRewardAdd(){

        return "manager/user/userRewardAdd";
    }

    @RequestMapping("/doUserRewardAdd")
    @ResponseBody
    public Rest doUserRewardAdd(UserReward userReward){

        UserRewardService.userRewardAdd(userReward);

        return Rest.ok();
    }

    @RequestMapping("/UserRewardEdit/{id}")
    public String UserRewardEdit(@PathVariable int id, Model model){

        model.addAttribute("userReward",UserRewardService.selectByIdA(id));
        return "manager/user/userRewardEdit";
    }

    @RequestMapping("/doUserRewardEdit")
    @ResponseBody
    public Rest doUserRewardEdit(UserReward userReward){

        UserRewardService.userRewardEdit(userReward);

        return Rest.ok();
    }
    @RequestMapping("/updateState")
    @ResponseBody
    public AjaxResult updateState(@RequestParam("id")Integer id, @RequestParam("state")Integer state){

        try{
            UserRewardService.updateState(id,state);
            ajaxResult.ajaxTrue("成功");
        }catch(Exception e){
            ajaxResult.ajaxFalse("失败");
            e.printStackTrace();
        }
        return ajaxResult;
    }
    @RequestMapping("/delUser/{id}")
    @ResponseBody
    public AjaxResult delUser(@PathVariable("id") Integer id){
        try{
            userService.delUser(id);
            ajaxResult.ajaxTrue("删除成功");
        }catch (Exception e){
            ajaxResult.ajaxFalse("系统错误!");
            e.printStackTrace();
        }
        return ajaxResult;
    }
}
