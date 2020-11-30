package com.zero.system.controller.siteConfiguration;


import com.zero.system.entity.siteConfiguration.SiteConfiguration;
import com.zero.system.service.SiteConfigurationService;
import com.zero.system.service.UserRewardService;
import com.zero.system.util.Rest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/site")
public class SiteConfigurationController {


    @Autowired
    private SiteConfigurationService siteConfigurationService;
    @Autowired
    private UserRewardService userRewardService;

    @RequestMapping("/update")
    public String update( Model model){
        SiteConfiguration siteConfiguration = siteConfigurationService.selectByIdA(1);
        model.addAttribute("siteConfiguration",siteConfiguration);
        model.addAttribute("userReward",userRewardService.selectList());
        return "manager/siteConfiguration/update";
    }

    @RequestMapping("/doUpdate")
    @ResponseBody
    public Rest doUpdate(SiteConfiguration siteConfiguration){


        siteConfigurationService.updateByIdA(siteConfiguration);

        return Rest.ok();
    }

}
