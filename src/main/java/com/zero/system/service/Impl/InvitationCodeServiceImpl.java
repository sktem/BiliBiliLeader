package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zero.system.entity.channel.InvitationCode;
import com.zero.system.mapper.InvitationCodeMapper;
import com.zero.system.service.InvitationCodeService;
import com.zero.system.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/9
 */
@Service
public class InvitationCodeServiceImpl extends ServiceImpl<InvitationCodeMapper, InvitationCode> implements InvitationCodeService {
 @Autowired
  private InvitationCodeMapper invitationCodeMapper;
    @Override
    public PageBean<InvitationCode> queryPage(Map<String, Object> paramMap) {
        PageBean<InvitationCode> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<InvitationCode> datas = invitationCodeMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = invitationCodeMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public void addInvitationCode(List<InvitationCode> list) {
        invitationCodeMapper.addInvitationCode(list);
    }

    @Override
    public void updateUid(Integer uId, String code,String uName) {
        invitationCodeMapper.updateUid(uId,code, uName);
    }

    @Override
    public InvitationCode selectByCode(String code) {
        return baseMapper.selectByCode(code);
    }

    @Override
    public InvitationCode veryUser(Integer uId) {
        return baseMapper.veryUser(uId);
    }

    @Override
    public void delectAll() {
        baseMapper.delectAll();
    }


}
