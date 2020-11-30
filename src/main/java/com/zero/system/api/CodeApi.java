package com.zero.system.api;

import com.zero.system.entity.channel.InvitationCode;
import com.zero.system.entity.user.User;
import com.zero.system.service.InvitationCodeService;
import com.zero.system.service.OrderService;
import com.zero.system.service.UserService;
import com.zero.system.util.AjaxResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/10
 */
@RestController
@RequestMapping("/api/code")
public class CodeApi {
    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private InvitationCodeService invitationCodeService;
    @Autowired
    private UserService userService;

    @RequestMapping("/verification")
    public AjaxResult verification(@Param("uId") Integer uId,@Param("code") String code){
        try {
            InvitationCode icode = invitationCodeService.selectByCode(code);
            User user=userService.selectByIdA(uId);
            Date date=new Date();
            if(null != icode){
                if(icode.getUId()!=0){
                    ajaxResult.ajaxFalse("该邀请码已被激活!");
                    return ajaxResult;
                }else if(icode.getState()==1){
                    ajaxResult.ajaxFalse("该邀请码已被禁用!");
                    return ajaxResult;
                }else if(date.getTime()>icode.getOverdueTime().getTime()){
                    ajaxResult.ajaxFalse("该邀请码已过期!");
                    return ajaxResult;
                }
                invitationCodeService.updateUid(uId,code,user.getUserName());
                ajaxResult.ajaxTrue("验证成功!");
            }else
            {
                ajaxResult.ajaxFalse("该邀请码不存在!");
                return ajaxResult;
            }



        }catch (Exception e){
            ajaxResult.ajaxFalse("系统繁忙!");
            e.printStackTrace();
        }
    return ajaxResult;
}
@RequestMapping("/veryUser")
public AjaxResult vieyUser(@Param("uId")Integer uId){
        try{
            InvitationCode icode=invitationCodeService.veryUser(uId);
            if(icode==null){
                ajaxResult.ajaxFalse("");
            }else{
                ajaxResult.ajaxTrue("");
            }
        }catch (Exception e){
            ajaxResult.ajaxFalse("系统繁忙!");
            e.printStackTrace();
        }
        return ajaxResult;
}


}
