package cn.figo.mydemo.bean;

/**
 * User: Ligx
 * Date: 2016-10-28
 * Time: 11:14
 * Copyright (c) 2016年 Lgx. All rights reserved.
 */
public class BangumiPlayBean {


    /**
     * from : local
     * result : suee
     * format : mp4
     * timelength : 1472028
     * accept_format : mp4,hdmp4
     * accept_quality : {"0":2,"1":1}
     * seek_param : start
     * seek_type : second
     * durl : {"0":{"order":1,"length":1472028,"size":108066785,"url":"http://cn-gdsz-cmcc-v-01.acgvideo.com/vg5/c/aa/10727681-1.mp4?expires=1477637700&ssig=1U3ao_qO5pr9-2WE9pzt8w&oi=3085493157&rate=200000","backup_url":{"0":"http://cn-gdgz2-cmcc.acgvideo.com/vg3/a/92/10727681-1.mp4?expires=1477637700&ssig=JIGqZZABh3yzXSyKSWSwNA&oi=3085493157&rate=200000","1":"http://cn-sccd1-cmcc.acgvideo.com/vg1/2/89/10727681-1.mp4?expires=1477637700&ssig=F5cg4js0fnPYFVK8UzQPMg&oi=3085493157&rate=200000"}}}
     * img : http://i0.hdslb.com/bfs/archive/a674cb5bdfbf900f3335a11bf24def60223e52f2.jpg
     * cid : http://comment.bilibili.com/10727681.xml
     */

    private String from;
    private String result;
    private String format;
    private int timelength;
    private String accept_format;
    /**
     * 0 : 2
     * 1 : 1
     */

    private AcceptQualityEntity accept_quality;
    private String seek_param;
    private String seek_type;
    /**
     * 0 : {"order":1,"length":1472028,"size":108066785,"url":"http://cn-gdsz-cmcc-v-01.acgvideo.com/vg5/c/aa/10727681-1.mp4?expires=1477637700&ssig=1U3ao_qO5pr9-2WE9pzt8w&oi=3085493157&rate=200000","backup_url":{"0":"http://cn-gdgz2-cmcc.acgvideo.com/vg3/a/92/10727681-1.mp4?expires=1477637700&ssig=JIGqZZABh3yzXSyKSWSwNA&oi=3085493157&rate=200000","1":"http://cn-sccd1-cmcc.acgvideo.com/vg1/2/89/10727681-1.mp4?expires=1477637700&ssig=F5cg4js0fnPYFVK8UzQPMg&oi=3085493157&rate=200000"}}
     */

    private DurlEntity durl;

    public static class AcceptQualityEntity {
        @com.google.gson.annotations.SerializedName("0")
        private int value0;
        @com.google.gson.annotations.SerializedName("1")
        private int value1;

        public int getValue0() {
            return value0;
        }

        public void setValue0(int value0) {
            this.value0 = value0;
        }

        public int getValue1() {
            return value1;
        }

        public void setValue1(int value1) {
            this.value1 = value1;
        }
    }
}
