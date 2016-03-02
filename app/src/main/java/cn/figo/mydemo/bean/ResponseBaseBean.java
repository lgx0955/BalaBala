package cn.figo.mydemo.bean;

/**
 * User: Ligx
 * Date: 2016-02-26
 * Time: 10:27
 * Copyright (c)Ligx All rights reserved.
 */
public class ResponseBaseBean extends BaseBean{
    int code = 0;

    public ResponseBaseBean() {
    }
    public ResponseBaseBean(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
