package com.zero.system.entity.withdrawal;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class Withdrawal {

    private int id; //id
    private Integer userId; //用户Id
    private String orderNo; //系统订单号
    private String withdrawalMoney; //提现金额
    private int state; //状态
    private Date createTime; //提交时间
    private Date withdrawalTime;//打款时间
    private String paymentOrder;//云代付订单号
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
