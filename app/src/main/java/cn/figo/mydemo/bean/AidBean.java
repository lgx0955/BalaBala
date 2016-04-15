package cn.figo.mydemo.bean;

/**
 * User: Ligx
 * Date: 2016-03-11
 * Time: 17:31
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class AidBean extends BaseBean{

    /**
     * img : http://i1.hdslb.com/video/ab/ab955bd5a42b58119e8cc60db18a4bb7.jpg
     * cid : http://comment.bilibili.com/5635675.xml
     * src : http://cn-gdjm2-dx.acgvideo.com/vg20/5/2c/5635675.mp4?expires=1457703000&ssig=GfG42KVjgeF6yNSEIy_6uA&oi=2005029599&internal=1&rate=0
     */

    private String img;
    private String cid;
    private String src;

    public void setImg(String img) {
        this.img = img;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getImg() {
        return img;
    }

    public String getCid() {
        return cid;
    }

    public String getSrc() {
        return src;
    }
}
