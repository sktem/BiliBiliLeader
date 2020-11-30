package com.zero.system.entity.channel;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 渠道实体类
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/9
 */
@Data
public class Channel {
    /**
     * ID
     */
    private Integer Id;
    /**
     * 渠道名称
     */
    private String name;
    /**
     * 状态 0:启用 1:禁用
     */
    private Integer state;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date updateTime;
}
