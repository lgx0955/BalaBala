package cn.figo.mydemo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.figo.mydemo.adapter.AdapterModel;

/**
 * User: Ligx
 * Date: 2016-02-25
 * Time: 14:24
 * Copyright (c)Ligx All rights reserved.
 */
public class RecommendBean extends ResponseBaseBean implements AdapterModel {

    private String message;
    private String screen;
    private String ver;

    private List<ResultEntity> result;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public String getScreen() {
        return screen;
    }

    public String getVer() {
        return ver;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    @Override
    public int getDataTypeCount() {
        return 0;
    }

    @Override
    public Object getDataType() {
        return null;
    }

    public static class ResultEntity implements AdapterModel{
        /**
         * goto :
         * param :
         * style : gm_av
         * title : 热门焦点
         */

        private HeadEntity head;
        private String type;
        /**
         * cover : http://i0.hdslb.com/group1/M00/27/6F/oYYBAFbMX7qASLenAAE4SVfsYiM758.jpg_320x200.jpg
         * danmaku : 3603
         * desc1 : 自制 和了柠檬汁后的直播录的，哈哈哈哈
         上期视频，画的是KB（￣▽￣）：av3260424
         * desc2 : UP：吃素的狮子
         * goto : av
         * height : 301
         * is_random : 1
         * param : 3933083
         * play : 7.0万
         * small_cover : http://i0.hdslb.com/group1/M00/27/6F/oYYBAFbMX7qASLenAAE4SVfsYiM758.jpg_320x200.jpg
         * style : gm_av
         * title : 【狮子的绘画课堂4】这不是普通的柠檬！
         * width : 480
         */

        private List<BodyEntity> body;

        public void setHead(HeadEntity head) {
            this.head = head;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setBody(List<BodyEntity> body) {
            this.body = body;
        }

        public HeadEntity getHead() {
            return head;
        }

        public String getType() {
            return type;
        }

        public List<BodyEntity> getBody() {
            return body;
        }

        @Override
        public int getDataTypeCount() {
            return 0;
        }

        @Override
        public Object getDataType() {
            return getType();
        }

        public static class HeadEntity {
            @SerializedName("goto")
            private String gotoX;
            private String param;
            private String style;
            private String title;

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getGotoX() {
                return gotoX;
            }

            public String getParam() {
                return param;
            }

            public String getStyle() {
                return style;
            }

            public String getTitle() {
                return title;
            }
        }

        public static class BodyEntity implements AdapterModel{
            private String cover;
            private String danmaku;
            private String desc1;
            private String desc2;
            @SerializedName("goto")
            private String gotoX;
            private int height;
            private String is_random;
            private String param;
            private String play;
            private String small_cover;
            private String style;
            private String title;
            private int width;

            public void setCover(String cover) {
                this.cover = cover;
            }

            public void setDanmaku(String danmaku) {
                this.danmaku = danmaku;
            }

            public void setDesc1(String desc1) {
                this.desc1 = desc1;
            }

            public void setDesc2(String desc2) {
                this.desc2 = desc2;
            }

            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public void setIs_random(String is_random) {
                this.is_random = is_random;
            }

            public void setParam(String param) {
                this.param = param;
            }

            public void setPlay(String play) {
                this.play = play;
            }

            public void setSmall_cover(String small_cover) {
                this.small_cover = small_cover;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public String getCover() {
                return cover;
            }

            public String getDanmaku() {
                return danmaku;
            }

            public String getDesc1() {
                return desc1;
            }

            public String getDesc2() {
                return desc2;
            }

            public String getGotoX() {
                return gotoX;
            }

            public int getHeight() {
                return height;
            }

            public String getIs_random() {
                return is_random;
            }

            public String getParam() {
                return param;
            }

            public String getPlay() {
                return play;
            }

            public String getSmall_cover() {
                return small_cover;
            }

            public String getStyle() {
                return style;
            }

            public String getTitle() {
                return title;
            }

            public int getWidth() {
                return width;
            }

            @Override
            public int getDataTypeCount() {
                return 0;
            }

            @Override
            public Object getDataType() {
                return null;
            }
        }
    }
}
