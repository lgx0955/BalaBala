package cn.figo.mydemo.danmaku;

import java.util.List;

/**
 * User: Ligx
 * Date: 2016-03-09
 * Time: 14:22
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class SimpleDanmakuModel {
    private String cmd;
    private List<Object> info;

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setInfo(List<Object> info) {
        this.info = info;
    }

    public String getCmd() {
        return cmd;
    }

    public List<Object> getInfo() {
        return info;
    }
}
