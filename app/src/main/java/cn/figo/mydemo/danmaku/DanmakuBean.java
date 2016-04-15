package cn.figo.mydemo.danmaku;

import java.util.List;

/**
 * User: Ligx
 * Date: 2016-03-09
 * Time: 14:40
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class DanmakuBean {

    /**
     * info : [[0,1,25,16777215,1457530865,"1457530846",0,"c5c472fa",0],"B克拉收好",[5771796,"尛鑫鑫",0,0,0],[1,"姬控","请叫我毒姬",44592,9953448],[4,958754],[]]
     * cmd : DANMU_MSG
     */

    private String cmd;//DANMU_MSG  SEND_GIFT
    private List<List<Integer>> info;
    String msg;
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setInfo(List<List<Integer>> info) {
        this.info = info;
    }

    public String getCmd() {
        return cmd;
    }

    public List<List<Integer>> getInfo() {
        return info;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
