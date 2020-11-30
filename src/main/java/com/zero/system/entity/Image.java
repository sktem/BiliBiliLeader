package com.zero.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 收款码实体类
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/10
 */
@TableName("identification")
@Data
public class Image {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    @TableField("reward_id")
    private Integer rewardId;
    @TableField("image")
    private String image;
    @TableField("state")
    private Integer state;
    @TableField("identification")
    private String identification;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date updateTime;
    private  String rewardName;

}
