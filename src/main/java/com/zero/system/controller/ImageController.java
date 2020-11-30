package com.zero.system.controller;

import com.zero.system.entity.Image;
import com.zero.system.entity.user.UserReward;
import com.zero.system.service.ImageService;
import com.zero.system.service.UserRewardService;
import com.zero.system.util.AjaxResult;
import com.zero.system.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/10
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRewardService userRewardService;
    @Autowired
    private AjaxResult ajaxResult;
    /**
     * 跳转渠道列表页面
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "manager/image/imageList";
    }



    @RequestMapping("/imageList")
    @ResponseBody
    public Object channelList(@RequestParam(value = "page", defaultValue = "1") Integer pageno,
                              @RequestParam(value = "limit", defaultValue = "5") Integer pagesize,
                              String name){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",pageno);
        paramMap.put("pagesize",pagesize);

        //判断是否为空
        if(!StringUtils.isEmpty(name)) paramMap.put("rewardName",name);


        PageBean<Image> pageBean = imageService.queryPage(paramMap);

        Map<String,Object> rest = new HashMap();
        rest.put("code", 0);
        rest.put("msg", "");
        rest.put("count",pageBean.getTotalsize());
        rest.put("data", pageBean.getDatas());
        return rest;
    }
    @GetMapping("/addImage")
    public String addChannel(String type, Integer id, Model model){
        List<UserReward>list= userRewardService.selectList();
        model.addAttribute("list",list);
        if(type != null && type.equals("edit")){
            Image image=imageService.selectByIdA(id);
            model.addAttribute("image",image);
            model.addAttribute("images",image.getImage());
            model.addAttribute("identification",image.getIdentification());
            model.addAttribute("state",image.getState());

            model.addAttribute("rid",image.getRewardId());
        }
        return "manager/image/addImage";
    }
    @PostMapping("/addImage")
    @ResponseBody
    public AjaxResult addImage(Image image){
        try {
            if (image.getId() != null) {
                imageService.addImage(image,image.getRewardId());
                ajaxResult.ajaxTrue("修改成功!");
            } else {
                image.setState(1);
                imageService.addImage(image,image.getRewardId());

                ajaxResult.ajaxTrue("添加成功!");
            }
        }catch (Exception e){
            ajaxResult.ajaxFalse("系统错误!请联系管理员");
            e.printStackTrace();
        }
        return ajaxResult;
    }
    @RequestMapping("/updateState")
    @ResponseBody
    public  AjaxResult updateState(@RequestParam("id")Integer id,@RequestParam("state")Integer state){

        try{
            imageService.updateState(id,state);
            ajaxResult.ajaxTrue("成功");
        }catch(Exception e){
            ajaxResult.ajaxFalse("失败");
            e.printStackTrace();
        }
        return ajaxResult;
    }
    @PostMapping("/del/{id}")
    @ResponseBody
    public  AjaxResult delImage(@PathVariable("id")Integer id){

        try{
            imageService.delImage(id);
            ajaxResult.ajaxTrue("成功");
        }catch(Exception e){
            ajaxResult.ajaxFalse("失败");
            e.printStackTrace();
        }
        return ajaxResult;
    }
}
