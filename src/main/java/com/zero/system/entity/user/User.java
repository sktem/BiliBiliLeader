package com.zero.system.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;
    private String openId;//微信公众号openid
    private String openTxId;//云代付openid
    private String userName;
    private String image;
    private String sex;
    private int rewardId;
    private String balance;
    private String withdrawal;
    private int state;
    private Date createTime;
    private Date lastTime;
    //当前页
    @TableField(exist = false)
    private Integer page=1;
    //页大小
    @TableField(exist = false)
    private Integer rows=5;
    // 总记录 数
    @TableField(exist = false)
    private Integer totalRecord;
    //总页数
    @TableField(exist = false)
    private Integer totalPage;

    //开始记录位置
    @TableField(exist = false)
    private Integer start;
    //邀请码
    @TableField(exist = false)
    private String invitationCode;


}
