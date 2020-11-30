package com.zero.system.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成订单号类
 */
public class GenerateNum {
    // 使用单例模式，不允许直接创建实例
    private GenerateNum() {}

    // 创建一个空实例对象，类需要用的时候才赋值
    private static GenerateNum g = null;

    // 单例模式--懒汉模式
    public static synchronized GenerateNum getInstance() {
        if (g == null) {
            g = new GenerateNum();
        }
        return g;
    }

    // 全局自增数
    private static int count = 0;

    // 每天最多生成多少订单（最好是像9999这种准备进位的值）
    private static final int total = 99999;

    // 格式化的时间字符串
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    // 获取当前时间年月日毫秒字符串
    private static String getNowDateStr() {
        return sdf.format(new Date());
    }

    // 记录上一次的时间，用来判断是否需要递增全局数
    private static String now = null;



    /*
     * 生成一个订单号
     */
    public synchronized String TixianOrder() {
        String datastr = getNowDateStr();
        if (datastr.equals(now)) {
            count++;// 自增
        } else {
            count = 1;
            now = datastr;
        }
        int countInteger = String.valueOf(total).length() - String.valueOf(count).length();// 算补位
        String bu = "";// 补字符串
        for (int i = 0; i < countInteger; i++) {
            bu += "0";
        }
        bu += String.valueOf(count);
        if (count >= total) {
            count = 0;
        }
        return datastr+"TX"+CodeUtil.generateShortUuid()+bu;
    }

    public synchronized String ShoukuanOrder() {
        String datastr = getNowDateStr();
        if (datastr.equals(now)) {
            count++;// 自增
        } else {
            count = 1;
            now = datastr;
        }
        int countInteger = String.valueOf(total).length() - String.valueOf(count).length();// 算补位
        String bu = "";// 补字符串
        for (int i = 0; i < countInteger; i++) {
            bu += "0";
        }
        bu += String.valueOf(count);
        if (count >= total) {
            count = 0;
        }
        return datastr+"SK"+CodeUtil.generateShortUuid()+bu;
    }
}