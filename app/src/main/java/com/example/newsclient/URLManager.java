package com.example.newsclient;

/**
 * Created by yls on 2017/6/27.
 */

public class URLManager {
    public final String[] channelId = new String[] {
            "T1348647909107",   // 头条
            "T1348648037603",   // 社会
            "T1348649580692",   // 科技
            "T1348648756099",   // 财经
            "T1348649079062",   // 体育
            "T1348654060988",   // 汽车
    };

    /**
     * 获取一页新闻数据
     * @param channelId 新闻类别id
     * @return
     */
    public static String getUrl(String channelId) {
        // http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
        return "http://c.m.163.com/nc/article/headline/" + channelId + "/0-20.html";
    }

    // 视频url路径
    public static final String VideoURL = //
            "http://c.m.163.com/nc/video/list/V9LG4B3A0/y/0-20.html";
}
