package com.zero.system.controller.invitationCode;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zero.system.entity.Admin;
import com.zero.system.entity.channel.InvitationCode;
import com.zero.system.entity.log.BilibiliLog;
import com.zero.system.entity.login.Level;
import com.zero.system.entity.login.LoginData;
import com.zero.system.entity.login.Sign;
import com.zero.system.entity.toubiApi.ToubiVerification;
import com.zero.system.entity.video.VideoList;
import com.zero.system.service.AdminService;
import com.zero.system.service.BilibiliLogService;
import com.zero.system.service.ChannelService;
import com.zero.system.service.InvitationCodeService;
import com.zero.system.util.ApiUtil;
import com.zero.system.util.CodeUtil;
import com.zero.system.util.Const;
import com.zero.system.util.Rest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/code")
public class InvitationCodeController {

    @Autowired
    private ChannelService channelService;



    @Autowired
    private BilibiliLogService bilibiliLogService;
    @Autowired
    private AdminService adminService;

    @RequestMapping("/create")
    public String create (Model model){
        model.addAttribute("channel",channelService.selectList());
        return "manager/invitationCode/createInvitationCode";
    }

    @RequestMapping("/doCreate")
    @ResponseBody
    public Rest doCreate (HttpServletRequest request){
        Admin ad = (Admin) request.getSession().getAttribute(Const.ADMIN);
        BilibiliLog bilibiliLog = bilibiliLogService.selectByAid(ad.getId());
        List<String> list = JSONUtil.toList(JSONUtil.parseArray( bilibiliLog.getText()), String.class);
        return Rest.okData(list);
    }

    @RequestMapping("/shoudong")
    @ResponseBody
    public Rest shoudong(HttpServletRequest request) throws IOException {
        Admin ad = (Admin) request.getSession().getAttribute(Const.ADMIN);
        Admin admin = adminService.selectById(ad.getId());
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
        }
        return Rest.ok();
    }



}
