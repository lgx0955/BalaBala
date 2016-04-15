package cn.figo.mydemo.bean;

/**
 * User: Ligx
 * Date: 2016-03-09
 * Time: 14:08
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LiveVideoSourceBean extends ResponseBaseBean{

    /**
     * msg :
     * data : http://wshls.acgvideo.com/live/live_9617619_6384511/playlist.m3u8
     */

    private String msg;
    private String data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public String getData() {
        return data;
    }
}
