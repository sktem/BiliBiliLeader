package com.zero.system.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
public class UserReward {

    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private double rewardMoney;
    private double rechargeMoney;
    private int state;
    private int sort;

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

    @TableField(exist = false)
    private Integer stateApp; //验证是否已开启

}
