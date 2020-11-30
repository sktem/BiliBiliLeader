package com.zero.system.controller;

import com.zero.system.entity.channel.Channel;
import com.zero.system.service.ChannelService;
import com.zero.system.util.AjaxResult;
import com.zero.system.util.Const;
import com.zero.system.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/9
 */
@Controller
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private ChannelService channelService;
    /**
     * 跳转渠道列表页面
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "manager/channel/channelList";
    }

    /**
     *异步加载渠道列表数据
     * @param pageno
     * @param pagesize
     * @param name
     * @return
     */
    @RequestMapping("/channelList")
    @ResponseBody
    public Object channelList(@RequestParam(value = "page", defaultValue = "1") Integer pageno,
                            @RequestParam(value = "limit", defaultValue = "5") Integer pagesize,
                            String name){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",pageno);
        paramMap.put("pagesize",pagesize);

        //判断是否为空
        if(!StringUtils.isEmpty(name)) paramMap.put("name",name);


        PageBean<Channel> pageBean = channelService.queryPage(paramMap);

        Map<String,Object> rest = new HashMap();
        rest.put("code", 0);
        rest.put("msg", "");
        rest.put("count",pageBean.getTotalsize());
        rest.put("data", pageBean.getDatas());
        return rest;
    }
    /**
     * 跳转添加渠道页面
     * @return
     */
    @GetMapping("/addChannel")
    public String addChannel(String type, Integer id, Model model){
        if(type != null && type.equals("edit")){
            Channel channel = channelService.selectById(id);

            model.addAttribute(channel);
        }
        return "manager/channel/addChannel";
    }
    @PostMapping("/addChannel")
    @ResponseBody
    public AjaxResult doAddChannel(Channel channel){

        if(channel.getId()!=null){

            Channel channel1=channelService.selectById(channel.getId());
            channel1.setUpdateTime(new Date());
            channel1.setName(channel.getName());
            channelService.updateById(channel1.getName(),channel.getId(),channel.getUpdateTime());
            ajaxResult.ajaxTrue("修改成功");
        }else{
            channel.setCreateTime(new Date());
            channel.setState(1);
       if(channelService.addChannel(channel.getName(),channel.getCreateTime(),channel.getState())){
           ajaxResult.ajaxTrue("新增成功");
       }else{
           ajaxResult.ajaxFalse("新增失败");
       }


        }
        return ajaxResult;
    }
    @RequestMapping("/updateState")
    @ResponseBody
    public  AjaxResult updateState(@RequestParam("id")Integer id,@RequestParam("state")Integer state){

        try{
            channelService.updateState(state,id);
            ajaxResult.ajaxTrue("成功");
        }catch(Exception e){
            ajaxResult.ajaxFalse("失败");
            e.printStackTrace();
        }
      return ajaxResult;
    }

}
