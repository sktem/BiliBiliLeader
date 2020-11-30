package com.zero.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zero.system.entity.channel.InvitationCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/9
 */
public interface InvitationCodeMapper extends BaseMapper<InvitationCode> {
    List<InvitationCode> queryList(Map<String, Object> paramMap);
    Integer queryCount(Map<String, Object> paramMap);

    void addInvitationCode(List<InvitationCode> list);
    InvitationCode selectByCode(@Param("code") String code);
    void updateUid(@Param("uId")Integer uId,@Param("code")String code,@Param("uName")String uName);
    InvitationCode veryUser(@Param("uId")Integer uId);

    void delectAll();
}
