package com.zero.system.controller.app;


import com.zero.system.entity.login.Sign;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zero.system.entity.Admin;
import com.zero.system.entity.MMPay;
import com.zero.system.entity.log.BilibiliLog;
import com.zero.system.entity.login.Level;
import com.zero.system.entity.login.LoginApi;
import com.zero.system.entity.login.LoginData;
import com.zero.system.entity.login.Sign;
import com.zero.system.entity.order.Order;
import com.zero.system.entity.siteConfiguration.SiteConfiguration;
import com.zero.system.entity.tixian.Data;
import com.zero.system.entity.tixian.tixianApi;
import com.zero.system.entity.toubiApi.ToubiVerification;
import com.zero.system.entity.user.User;
import com.zero.system.entity.user.UserReward;
import com.zero.system.entity.video.VideoData;
import com.zero.system.entity.video.VideoList;
import com.zero.system.entity.withdrawal.Withdrawal;
import com.zero.system.service.*;
import com.zero.system.util.*;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/app")
public class IndexController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private BilibiliLogService bilibiliLogService;

    @RequestMapping("/index")
    @ResponseBody
    public Rest index () throws IOException {
        List<Admin> adminList = adminService.selectAll();
        for(Admin admin:adminList){
            List<String> strings = new ArrayList<>();
            if(admin.getUserId() !=null && admin.getSESSDATA()!=null && admin.getCsrf()!=null){
                strings.add("运行开始时间:"+new Date());
                strings.add("---开始【登录】---");
                LoginData loginData = ApiUtil.login(admin.getSESSDATA());
                Level level = loginData.getLevel_info();
                strings.add("登录成功，用户名: "+loginData.getUname());
                strings.add("硬币余额: "+loginData.getMoney());
                strings.add("当前等级: "+level.getCurrent_level());
                List<VideoList> videoDataListPlay = ApiUtil.video(admin.getSESSDATA(),1);
                VideoList videoDataPlay = videoDataListPlay.get(0);
                strings.add("---结束【登录】---");
                strings.add("");
                strings.add("---开始【观看&分享视频】---");
                strings.add("获取随机视频:《"+videoDataPlay.getTitle()+"》");
                strings.add("---开始观看视频---");
                ApiUtil.videoPlay(admin.getSESSDATA(),videoDataPlay.getBvid());
                strings.add("---视频观看完毕---");
                strings.add("---开始分享视频---");
                ApiUtil.videoShare(admin.getCsrf(),admin.getSESSDATA(),videoDataPlay.getBvid());
                strings.add("---开始分享完毕---");
                strings.add("");
                strings.add("---开始【投币】---");
                if(loginData.getMoney()>0){

                    ToubiVerification toubiVerification = ApiUtil.toubiVerification(admin.getSESSDATA());
                    if(toubiVerification.getData()==50){
                        strings.add("---今日已投5枚硬币,再投也不会有经验啦!!---");
                    }else{
                        List<VideoList> videoDataList = ApiUtil.video(admin.getSESSDATA(),50);
                        for(int i=0;i<5;i++){
                            VideoList videoData = videoDataList.get(i);
                            boolean flag = ApiUtil.verificationVideo(admin.getSESSDATA(),videoData.getBvid());
                            if(flag== true){
                                ApiUtil.toubi(admin.getSESSDATA(),admin.getCsrf(),videoData.getBvid());
                                strings.add("为《"+videoData.getTitle()+"》投币成功");
                            }else{
                                i--;
                            }
                        }
                    }
                }else{
                    strings.add("---投币结束,原因:硬币余额不足!---");
                }
                strings.add("---结束【投币】---");
                strings.add("---开始【直播中心签到】---");
                Sign sign = ApiUtil.sign(admin.getSESSDATA());
                if(sign.getMessage().equals("0")){
                    strings.add("直播中心签到成功!");
                }else{
                    strings.add(sign.getMessage());
                }

                strings.add("---结束【直播中心签到】---");
                strings.add("");
                strings.add("");
                strings.add("---全部任务已完成---");
                String json = JSONUtil.toJsonStr(strings);
                BilibiliLog bilibiliLog = new BilibiliLog();
                bilibiliLog.setCreateTime(new Date());
                bilibiliLog.setAid(admin.getId());
                bilibiliLog.setText(json);
                bilibiliLogService.BilibiliLogAdd(bilibiliLog);
/*
                ApiUtil.serverJ("SCU125807T9f4e1b3f876ce318467795ae1426e1185fac1417a2f3b",strings);
*/
            }
        }
        return Rest.ok();
    }





}
