package com.zero.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zero.system.entity.channel.InvitationCode;
import com.zero.system.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/9
 */
public interface InvitationCodeService  extends IService<InvitationCode> {
    PageBean<InvitationCode> queryPage(Map<String, Object> paramMap);

    void addInvitationCode(List<InvitationCode> list);
    InvitationCode selectByCode(String code);
    void updateUid (Integer uId,String code,String uName);
    InvitationCode veryUser(Integer uId);

    void delectAll();

}
