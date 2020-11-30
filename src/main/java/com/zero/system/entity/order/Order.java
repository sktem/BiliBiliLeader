package com.zero.system.entity.order;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.util.Date;

@Data
public class Order {

    private int id; //id
    private String orderNo;//系统订单号
    private int userId;//用户信息
    private int rewardId;//奖励ID
    private String rewardName;//奖励名称
    private int state;//状态
    private double payMoney;//支付金额
    private double rewardMoney;//奖励金额
    private Date createTime;//提交时间
    private Date updateTime;//确认时间
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
}
