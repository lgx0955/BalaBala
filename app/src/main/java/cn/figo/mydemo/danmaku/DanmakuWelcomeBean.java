package cn.figo.mydemo.danmaku;

/**
 * User: Ligx
 * Date: 2016-03-10
 * Time: 14:03
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class DanmakuWelcomeBean {

    /**
     * cmd : WELCOME
     * data : {"isadmin":0,"svip":1,"uid":383103,"uname":"寒假作业还没写完"}
     * roomid : 5440
     */

    private String cmd;
    /**
     * isadmin : 0
     * svip : 1
     * uid : 383103
     * uname : 寒假作业还没写完
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
        private int isadmin;
        private int svip;
        private int uid;
        private String uname;

        public void setIsadmin(int isadmin) {
            this.isadmin = isadmin;
        }

        public void setSvip(int svip) {
            this.svip = svip;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public int getIsadmin() {
            return isadmin;
        }

        public int getSvip() {
            return svip;
        }

        public int getUid() {
            return uid;
        }

        public String getUname() {
            return uname;
        }
    }
}
