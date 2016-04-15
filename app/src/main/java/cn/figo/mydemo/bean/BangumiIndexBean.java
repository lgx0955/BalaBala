package cn.figo.mydemo.bean;

import java.util.List;

/**
 * User: Ligx
 * Date: 2016-03-01
 * Time: 16:43
 * Copyright (c)Ligx All rights reserved.
 */
public class BangumiIndexBean extends ResponseBaseBean{

    private String ver;
    private String screen;
    private int count;

    private List<ListEntity> list;

    public void setVer(String ver) {
        this.ver = ver;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public String getVer() {
        return ver;
    }

    public String getScreen() {
        return screen;
    }

    public int getCount() {
        return count;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private String title;
        private String remark;
        private String remark2;
        private String style;
        private String imagekey;
        private String imageurl;
        private int width;
        private int height;
        private String type;
        private String spname;
        private int spid;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public void setRemark2(String remark2) {
            this.remark2 = remark2;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public void setImagekey(String imagekey) {
            this.imagekey = imagekey;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setSpname(String spname) {
            this.spname = spname;
        }

        public void setSpid(int spid) {
            this.spid = spid;
        }

        public String getTitle() {
            return title;
        }

        public String getRemark() {
            return remark;
        }

        public String getRemark2() {
            return remark2;
        }

        public String getStyle() {
            return style;
        }

        public String getImagekey() {
            return imagekey;
        }

        public String getImageurl() {
            return imageurl;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String getType() {
            return type;
        }

        public String getSpname() {
            return spname;
        }

        public int getSpid() {
            return spid;
        }
    }
}
