package com.zero.system.controller.wechat;


import com.zero.system.entity.Admin;
import com.zero.system.entity.code.LoginInfoData;
import com.zero.system.entity.code.UrlData;
import com.zero.system.entity.siteConfiguration.SiteConfiguration;
import com.zero.system.entity.wechat.WeChat;
import com.zero.system.service.AdminService;
import com.zero.system.service.WeChatService;
import com.zero.system.util.ApiUtil;
import com.zero.system.util.Const;
import com.zero.system.util.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/bilibili")
public class WeChatController {


    @Autowired
    private WeChatService weChatService;
    @Autowired
    private AdminService adminService;

    @RequestMapping("/cookieSetting")
    public String update(Model model, HttpServletRequest request){
        Admin ad = (Admin) request.getSession().getAttribute(Const.ADMIN);
        Admin admin = adminService.selectById(ad.getId());
        model.addAttribute("Admin",admin);
        return "manager/wechat/wechat";
    }

    @RequestMapping("/bilibiliCode")
    @ResponseBody
    public Rest bilibiliCode() throws IOException {
         UrlData urlData = ApiUtil.bilibiliCode();
        return Rest.okData(urlData);
    }

    @RequestMapping("/bilibiliLoginInfo")
    @ResponseBody
    public Rest bilibiliLoginInfo(String oauthKey) throws IOException {
        LoginInfoData loginInfoData = null;
        Integer num = null;
        Object object= ApiUtil.bilibiliLoginInfo(oauthKey);
        if(object.getClass().equals(Integer.class)){
            num = (Integer)object;
        }else{
            loginInfoData =(LoginInfoData) object;
        }
        if(null!=num){
            return Rest.okData(num);
        }else{
            return Rest.okData(loginInfoData.getUrl());
        }

    }
    @RequestMapping("/doUpdate")
    @ResponseBody
    public Rest doUpdate(Admin admin){
        Admin admin1 = adminService.selectById(admin.getId());
        admin1.setSESSDATA(admin.getSESSDATA());
        admin1.setCsrf(admin.getCsrf());
        admin1.setUserId(admin.getUserId());
        adminService.editByAdmin(admin1);
        return Rest.ok();
    }


}
