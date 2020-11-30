package com.zero.system.entity.siteConfiguration;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
public class SiteConfiguration {
    @TableId(type = IdType.AUTO)
    private int id; // id
    private String name; //网站名称
    private String logoUrl;//网站logo
    private String qq;//联系QQ
    private int rewardId;//会员奖励ID  最高限制
    private String tips;//限制提示
    private String tipsLast;//最后一关限制提示
    private String title;//网站标题
    private String keyword;//网站关键字
    private String entryDomainName;//入口域名

}
