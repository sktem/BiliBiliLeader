package com.zero.system.controller;

import com.zero.system.util.AjaxResult;
import com.zero.system.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/10
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private AjaxResult ajaxResult;
    @RequestMapping("/img")
    @ResponseBody
    public AjaxResult uploadImage(@RequestParam("file") MultipartFile imgFile){
        if (imgFile.isEmpty()) {
            // 设置错误状态码
           ajaxResult.ajaxFalse("");
            return ajaxResult;
        }
        // 拿到文件名
        String filename = imgFile.getOriginalFilename();

        // 存放上传图片的文件夹
        File fileDir = UploadUtils.getImgDirFile();
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
//            System.out.println(newFile.getAbsolutePath());

            // 上传图片到 -》 “绝对路径”
            imgFile.transferTo(newFile);
            ajaxResult.ajaxTrue("http://193.42.25.42:8088/upload/imgs/"+filename);
        } catch (IOException e) {
            ajaxResult.ajaxFalse("");
            e.printStackTrace();
        }
        return ajaxResult;

    }
}
