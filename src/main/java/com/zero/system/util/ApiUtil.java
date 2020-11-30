package com.zero.system.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zero.system.entity.code.*;
import com.zero.system.entity.login.LoginApi;
import com.zero.system.entity.login.LoginData;
import com.zero.system.entity.login.Sign;
import com.zero.system.entity.toubiApi.ToubiApi;
import com.zero.system.entity.toubiApi.ToubiVerification;
import com.zero.system.entity.video.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtil {




    public static List<VideoList> video(String SESSDATA,int ps) throws IOException {
        String videoPath = "http://api.bilibili.com/x/web-interface/dynamic/region?rid=21&ps="+ps+"&pn=1";//获取分区视频排行榜列表
        String cookie = "SESSDATA="+SESSDATA+";";
        JSONObject videoObject = sendHttpGet(videoPath,cookie);

        VideoApi videoApi = JSON.parseObject(videoObject.toJSONString(), VideoApi.class);

        List<VideoList> videoList = videoApi.getData().getArchives();
        return videoList;
    }

    public static String toubi(String SESSDATA, String csrf, String bvid) {
        String toubiPath = "http://api.bilibili.com/x/web-interface/coin/add";//投币
        String cookie = "SESSDATA="+SESSDATA+";";
        HashMap<String,String> toubiMap = new HashMap<>();
        toubiMap.put("bvid",bvid);
        toubiMap.put("multiply","1");
        toubiMap.put("csrf",csrf);
        JSONObject toubiMapObject = sendHttpPost(toubiPath,toubiMap,cookie);
        ToubiApi toubiApi = JSON.parseObject(toubiMapObject.toJSONString(), ToubiApi.class);
        System.out.println("消息提醒:"+ toubiApi.getMessage());
        return toubiApi.getMessage();
    }
    public static LoginData login(String SESSDATA) throws IOException {
        String loginPath = "http://api.bilibili.com/x/web-interface/nav";//投币
        String cookie = "SESSDATA="+SESSDATA+";";
        JSONObject toubiMapObject = sendHttpGet(loginPath,cookie);
        LoginApi loginApi = JSON.parseObject(toubiMapObject.toJSONString(), LoginApi.class);
        LoginData loginData = loginApi.getData();
        return loginData;
    }

    public static boolean verificationVideo(String SESSDATA,String bvid) throws IOException {
        String verificationVideoPath = "http://api.bilibili.com/x/web-interface/archive/coins?bvid="+bvid;//投币
        String cookie = "SESSDATA="+SESSDATA+";";
        JSONObject verificationVideoMapObject = sendHttpGet(verificationVideoPath,cookie);
        VerificationVideo verificationVideo = JSON.parseObject(verificationVideoMapObject.toJSONString(), VerificationVideo.class);
        VerificationVideoData verificationVideoData = verificationVideo.getData();
        if(verificationVideoData.getMultiply()==0){
            return true;
        }else{
            return false;
        }

    }

    public static UrlData bilibiliCode() throws IOException {
        String bilibiliCodePath = "http://passport.bilibili.com/qrcode/getLoginUrl";//投币
        JSONObject bilibiliCodeObject = sendHttpGet(bilibiliCodePath,null);
        Url url = JSON.parseObject(bilibiliCodeObject.toJSONString(), Url.class);
        UrlData urlData = url.getData();
        return urlData;

    }

    public static Object bilibiliLoginInfo(String oauthKey) throws IOException {
        String bilibiliCodePath = "http://passport.bilibili.com/qrcode/getLoginInfo?oauthKey="+oauthKey;//投币
        HashMap<String,String> bilibiliLoginInfoMap = new HashMap<>();
        bilibiliLoginInfoMap.put("oauthKey",oauthKey);
        JSONObject bilibiliCodeObject = sendHttpPost(bilibiliCodePath,bilibiliLoginInfoMap,null);
        LoginInfoData loginInfoData = null;
        Integer num = null;
        if(bilibiliCodeObject.get("data").getClass().equals(Integer.class)){
            LoginInfoInteger loginInfoInteger = JSON.parseObject(bilibiliCodeObject.toJSONString(), LoginInfoInteger.class);
            num = loginInfoInteger.getData();
        }else{
            LoginInfo loginInfo = JSON.parseObject(bilibiliCodeObject.toJSONString(), LoginInfo.class);
            loginInfoData = loginInfo.getData();
        }

        if(null!=num){
            return num;
        }else{
            return loginInfoData;
        }



    }

    public static VideoPlay videoPlay(String SESSDATA,String bvid) throws IOException {
        String videoPlayPath = "https://api.bilibili.com/x/click-interface/web/heartbeat?played_time=16&bvid="+bvid;//投币
        HashMap<String,String> videoPlayPathMap = new HashMap<>();
        videoPlayPathMap.put("played_time","16");
        videoPlayPathMap.put("bvid",bvid);
        String cookie = "SESSDATA="+SESSDATA+";";
        JSONObject bilibiliCodeObject = sendHttpPost(videoPlayPath,videoPlayPathMap,cookie);

        VideoPlay videoPlay= JSON.parseObject(bilibiliCodeObject.toJSONString(), VideoPlay.class);

        return videoPlay;


    }

    public static VideoShare videoShare(String csrf,String SESSDATA,String bvid) throws IOException {

        String videoSharePath = "https://api.bilibili.com/x/web-interface/share/add";//投币

        HashMap<String,String> videoShareMap = new HashMap<>();
        videoShareMap.put("csrf",csrf);
        videoShareMap.put("bvid",bvid);
        String cookie = "SESSDATA="+SESSDATA+";";
        JSONObject bilibiliCodeObject = sendHttpPost(videoSharePath,videoShareMap,cookie);

        VideoShare videoShare= JSON.parseObject(bilibiliCodeObject.toJSONString(), VideoShare.class);

        return videoShare;


    }

    public static Sign sign(String SESSDATA) throws IOException {

        String videoSharePath = "https://api.live.bilibili.com/xlive/web-ucenter/v1/sign/DoSign";//投币
        String cookie = "SESSDATA="+SESSDATA+";";
        JSONObject bilibiliCodeObject = sendHttpGet(videoSharePath,cookie);

        Sign sign= JSON.parseObject(bilibiliCodeObject.toJSONString(), Sign.class);

        return sign;


    }
    public static ToubiVerification toubiVerification(String SESSDATA) throws IOException {

        String videoSharePath = "https://api.bilibili.com/x/web-interface/coin/today/exp";//投币
        String cookie = "SESSDATA="+SESSDATA+";";
        JSONObject bilibiliCodeObject = sendHttpGet(videoSharePath,cookie);

        ToubiVerification toubiVerification= JSON.parseObject(bilibiliCodeObject.toJSONString(), ToubiVerification.class);

        return toubiVerification;


    }

    public static void serverJ(String SCKEY,List<String> list) throws IOException {
        String desp= "";
        for(String serverJ :list){
            desp+=serverJ;
        }
        String serverJPath = "https://sc.ftqq.com/"+SCKEY+".send?text=BiliBili每日任务日报 -gxe.me&desp="+desp;
        sendHttpGet(serverJPath,null);
    }











    public static JSONObject sendHttpPost(String url, HashMap<String, String> params, String cookie){
        URL realUrl = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        StringBuffer sb = new StringBuffer();
        if(params != null){
            //将哈希表参数转化为字符串
            for(Map.Entry<String,String>e :params.entrySet()){
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb=sb.deleteCharAt(sb.length() - 1);
        }
        String stringParams = sb.toString();


        //发送请求
        try{
            realUrl = new URL(url);
            conn = (HttpURLConnection)realUrl.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Cookie", cookie);
            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(stringParams);
            pw.flush();
            pw.close();
            in = conn.getInputStream();//获得返回的输入流
        }catch(Exception e){
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        String result = buffer.toString();//返回低字符串
        JSONObject josonResult = null;
        try{

            josonResult = JSONObject.parseObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return josonResult;

    }

    public static JSONObject sendHttpGet(String url, String cookie) throws IOException {
        URL urlGet = new URL(url);
        HttpURLConnection conn =(HttpURLConnection) urlGet.openConnection();
        conn.setRequestMethod("GET");
        if(cookie!=null){
            conn.setRequestProperty("Cookie", cookie);
        }

        conn.setDoInput(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder buffer = new StringBuilder();

        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        String result = buffer.toString();//返回低字符串
        JSONObject josonResult = null;
        try{

            josonResult = JSONObject.parseObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return josonResult;

    }

}
