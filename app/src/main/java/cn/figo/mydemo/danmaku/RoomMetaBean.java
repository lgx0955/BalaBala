package cn.figo.mydemo.danmaku;

/**
 * User: Ligx
 * Date: 2016-10-21
 * Time: 16:55
 * Copyright (c) 2016年 Lgx. All rights reserved.
 */
public class RoomMetaBean {

    Long uid;
    int roomid;

    public RoomMetaBean(int roomid, Long uid) {
        this.roomid = roomid;
        this.uid = uid;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public double getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
