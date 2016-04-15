package cn.figo.mydemo.danmaku;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * User: Ligx
 * Date: 2016-03-09
 * Time: 21:45
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class DanmakuGiftBean {

    /**
     * cmd : SEND_GIFT
     * data : {"giftName":"B坷垃","num":1,"uname":"尛鑫鑫","rcost":157422,"uid":5771796,"top_list":[],"timestamp":1457530897,"giftId":3,"giftType":0,"action":"赠送","super":1,"price":9900,"rnd":"1457530846","newMedal":0,"newTitle":0,"medal":1,"title":""}
     * roomid : 44592
     */

    private String cmd;
    /**
     * giftName : B坷垃
     * num : 1
     * uname : 尛鑫鑫
     * rcost : 157422
     * uid : 5771796
     * top_list : []
     * timestamp : 1457530897
     * giftId : 3
     * giftType : 0
     * action : 赠送
     * super : 1
     * price : 9900
     * rnd : 1457530846
     * newMedal : 0
     * newTitle : 0
     * medal : 1
     * title :
     */

    private DataEntity data;
    private int roomid;

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getCmd() {
        return cmd;
    }

    public DataEntity getData() {
        return data;
    }

    public int getRoomid() {
        return roomid;
    }

    public static class DataEntity {
        private String giftName;
        private int num;
        private String uname;
        private int rcost;
        private int uid;
        private int timestamp;
        private int giftId;
        private int giftType;
        private String action;
        @SerializedName("super")
        private int superX;
        private int price;
        private String rnd;
        private int newMedal;
        private int newTitle;
        private int medal;
        private String title;
        private List<?> top_list;

        public void setGiftName(String giftName) {
            this.giftName = giftName;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public void setRcost(int rcost) {
            this.rcost = rcost;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public void setGiftId(int giftId) {
            this.giftId = giftId;
        }

        public void setGiftType(int giftType) {
            this.giftType = giftType;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public void setSuperX(int superX) {
            this.superX = superX;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setRnd(String rnd) {
            this.rnd = rnd;
        }

        public void setNewMedal(int newMedal) {
            this.newMedal = newMedal;
        }

        public void setNewTitle(int newTitle) {
            this.newTitle = newTitle;
        }

        public void setMedal(int medal) {
            this.medal = medal;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTop_list(List<?> top_list) {
            this.top_list = top_list;
        }

        public String getGiftName() {
            return giftName;
        }

        public int getNum() {
            return num;
        }

        public String getUname() {
            return uname;
        }

        public int getRcost() {
            return rcost;
        }

        public int getUid() {
            return uid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public int getGiftId() {
            return giftId;
        }

        public int getGiftType() {
            return giftType;
        }

        public String getAction() {
            return action;
        }

        public int getSuperX() {
            return superX;
        }

        public int getPrice() {
            return price;
        }

        public String getRnd() {
            return rnd;
        }

        public int getNewMedal() {
            return newMedal;
        }

        public int getNewTitle() {
            return newTitle;
        }

        public int getMedal() {
            return medal;
        }

        public String getTitle() {
            return title;
        }

        public List<?> getTop_list() {
            return top_list;
        }
    }
}
