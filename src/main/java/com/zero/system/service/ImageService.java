package com.zero.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.zero.system.entity.Image;
import com.zero.system.util.PageBean;

import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/10
 */
public interface ImageService extends IService<Image> {
    PageBean<Image> queryPage(Map<String, Object> paramMap);
    void addImage(Image img,Integer rewardId);
    void updateRid(Integer rewardId,Integer id);
    Image selectByIdA(Integer id);
    void updateState(Integer id,Integer state);
    Image selectImage(Integer rewardId);
    void delImage(Integer id);

    void delectAll();

}
