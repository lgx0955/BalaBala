package cn.figo.mydemo.event;

import cn.figo.mydemo.bean.RecommendBean;

/**
 * User: Ligx
 * Date: 2016-02-26
 * Time: 13:44
 * Copyright (c)Ligx All rights reserved.
 */
public class RecommentEvent extends Event{
    RecommendBean recommendBean;

    public RecommentEvent(Object data) {
        super(data);
        recommendBean = (RecommendBean)data;
    }

    public RecommendBean getRecommendBean() {
        return recommendBean;
    }

    public void setRecommendBean(RecommendBean recommendBean) {
        this.recommendBean = recommendBean;
    }
}
