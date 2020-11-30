package com.zero.system.controller;

import com.zero.system.entity.channel.InvitationCode;
import com.zero.system.service.InvitationCodeService;
import com.zero.system.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/9
 */
@Controller
@RequestMapping("/icode")
public class ICodeController {
    @Autowired
    private InvitationCodeService invitationCodeService;
    /**
     * 跳转渠道列表页面
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "manager/icode/icodeList";
    }


    @RequestMapping("/icodeList")
    @ResponseBody
    public Object channelList(@RequestParam(value = "page", defaultValue = "1") Integer pageno,
                              @RequestParam(value = "limit", defaultValue = "5") Integer pagesize,
                              String icode, Integer state, Model model){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",pageno);
        paramMap.put("pagesize",pagesize);

        //判断是否为空
        if(!StringUtils.isEmpty(icode)) paramMap.put("icode",icode) ;
       paramMap.put("state",state);
       model.addAttribute("state",state);

        PageBean<InvitationCode> pageBean = invitationCodeService.queryPage(paramMap);

        Map<String,Object> rest = new HashMap();
        rest.put("code", 0);
        rest.put("msg", "");
        rest.put("count",pageBean.getTotalsize());
        rest.put("data", pageBean.getDatas());
        return rest;
    }
}
