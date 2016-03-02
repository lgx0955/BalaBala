package cn.figo.mydemo.event;

/**
 * User: Ligx
 * Date: 2016-02-26
 * Time: 13:52
 * Copyright (c)Ligx All rights reserved.
 */
public class Event<T> {
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Event(T data) {
        this.data = data;
    }
}
