package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zero.system.entity.Image;
import com.zero.system.mapper.ImageMapper;
import com.zero.system.service.ImageService;
import com.zero.system.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/10
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {
    @Autowired
    private ImageMapper imageMapper;
    @Override
    public PageBean<Image> queryPage(Map<String, Object> paramMap) {
        PageBean<Image> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Image> datas = imageMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = imageMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public void updateRid(Integer rewardId, Integer id) {
        baseMapper.updateRid(rewardId,id);
    }

    @Override
    public Image selectByIdA(Integer id) {
        return baseMapper.selectByIdA(id);
    }

    @Override
    public void addImage(Image img,Integer rewardId) {
    baseMapper.addImage(img,rewardId);
    }

    @Override
    public void updateState(Integer id, Integer state) {
        baseMapper.updateState(id,state);
    }

    @Override
    public Image selectImage(Integer rewardId) {
        List<Image>list=baseMapper.selectImage(rewardId);
        int size=0;
        int random =0;
        if (list!=null&&list.size()>0) {
             size = list.size();
             random=(int) (Math.random()*size);
            return list.get(random);
        }else{
            return null;
        }

    }

    @Override
    public void delImage(Integer id) {
        baseMapper.delImage(id);
    }

    @Override
    public void delectAll() {
        baseMapper.delectAll();
    }
}
