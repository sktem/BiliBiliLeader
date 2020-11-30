package com.zero.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zero.system.entity.Image;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/10
 */
public interface ImageMapper  extends BaseMapper<Image> {
    List<Image> queryList(Map<String, Object> paramMap);
    Integer queryCount(Map<String, Object> paramMap);
    void addImage(@Param("image") Image image,@Param("rewardId")Integer rewardId);
    void updateRid(Integer rewardId,Integer id);
    Image selectByIdA(Integer id);
    void updateState(Integer id,Integer state);
    List<Image> selectImage(@Param("rewardId")Integer rewardId);
    void delImage(@Param("id") Integer id);

    void delectAll();

}
