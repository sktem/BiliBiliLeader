package com.zero.system.entity.channel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 邀请码实体类
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/9
 */
@TableName("invitation_code")
@Data
public class InvitationCode {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("channel_id")
    private Integer channelId;
    @TableField("invitation_code")
    private String invitationCode;
    @TableField("u_id")
    private Integer uId;
    private Integer state;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date createTime;
    /**
     * 激活时间
     */
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date activationTime;
    /**
     * 过期时间
     */
    @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")
    private Date overdueTime;

    private String channelName;

}
