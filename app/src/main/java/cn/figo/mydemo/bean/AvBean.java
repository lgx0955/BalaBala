package cn.figo.mydemo.bean;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * User: Ligx
 * Date: 2016-11-26
 * Time: 02:11
 * Copyright (c) 2016年 Lgx. All rights reserved.
 */
@Root(name = "video",strict = false)
public class AvBean extends BaseBean{

    @Element(name = "result",data = true)
    private String result;
    @Element(name = "timelength",data = true)
    private String timelength;
    @Element(name = "format",data = true)
    private String format;
    @Element(name = "accept_format",data = true)
    private String accept_format;
    @Element(name = "accept_quality",data = true)
    private String accept_quality;
    @Element(name = "from",data = true)
    private String from;
    @Element(name = "seek_param",data = true)
    private String seek_param;
    @Element(name = "seek_type",data = true)
    private String seek_type;
    @Element(name = "bp",data = true)
    private String bp;
    @Element(name = "vip_status",data = true)
    private String vip_status;
    @Element(name = "vip_type",data = true)
    private String vip_type;
    @Element(name = "has_paid",data = true)
    private String has_paid;
    @ElementList(inline=true, required=false)
    private List<DurlEntity> durl;

    public String getAccept_format() {
        return accept_format;
    }

    public void setAccept_format(String accept_format) {
        this.accept_format = accept_format;
    }

    public String getSeek_param() {
        return seek_param;
    }

    public void setSeek_param(String seek_param) {
        this.seek_param = seek_param;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAccept_quality() {
        return accept_quality;
    }

    public void setAccept_quality(String accept_quality) {
        this.accept_quality = accept_quality;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getTimelength() {
        return timelength;
    }

    public void setTimelength(String timelength) {
        this.timelength = timelength;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSeek_type() {
        return seek_type;
    }

    public void setSeek_type(String seek_type) {
        this.seek_type = seek_type;
    }

    public String getHas_paid() {
        return has_paid;
    }

    public void setHas_paid(String has_paid) {
        this.has_paid = has_paid;
    }

    public String getVip_type() {
        return vip_type;
    }

    public void setVip_type(String vip_type) {
        this.vip_type = vip_type;
    }

    public String getVip_status() {
        return vip_status;
    }

    public void setVip_status(String vip_status) {
        this.vip_status = vip_status;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<DurlEntity> getDurl() {
        return durl;
    }

    public void setDurl(List<DurlEntity> durl) {
        this.durl = durl;
    }

    @Root(name = "durl")
    public static class DurlEntity {

        @Element(required=false)
        private String order;
        @Element(required=false)
        private String length;
        @Element(required=false)
        private String size;
        @Element(data = true,required=false)
        private String url;
        @ElementList(inline=false, required=false)
        private List<String> backup_url;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public List<String> getBackup_url() {
            return backup_url;
        }

        public void setBackup_url(List<String> backup_url) {
            this.backup_url = backup_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
