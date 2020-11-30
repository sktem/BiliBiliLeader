package com.zero.system.util;

import java.io.File;

/**
 * @author xiaozeng
 * @version 1.0
 * @date 2020/6/10
 */
public class UploadUtils {
    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    //public final static String IMG_PATH_PREFIX = "E:/upload/imgs";
    public final static String IMG_PATH_PREFIX = "/home/upload/imgs";
    public static File getImgDirFile(){

        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String(IMG_PATH_PREFIX);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

}
