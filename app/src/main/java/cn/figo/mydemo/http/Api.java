package cn.figo.mydemo.http;


import cn.figo.mydemo.bean.BangumiIndexBean;
import cn.figo.mydemo.bean.BanguminIndexHeaderBean;
import cn.figo.mydemo.bean.RecommendBannerBean;
import cn.figo.mydemo.bean.RecommendBean;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;


/**
 * User: Ligx
 * Date: 2016-01-12
 * Time: 10:05
 * Copyright (c)Ligx All rights reserved.
 */
public interface Api {
//http://www.touba.cc/index.php
    @Headers({
            "Content-type: application/json"
    })

    //获取首页推荐
    @GET("http://app.bilibili.com/api/index/9214/index.android.xxhdpi.android4.json")
    Call<RecommendBean> updateRecommend();

    //获取首页番剧
    @GET("http://app.bilibili.com/bangumi/operation_module?_device=android&_hwid=ac538400c68784bb&_ulv=10000&module=bangumi&platform=android&screen=xxhdpi")
    Call<BangumiIndexBean> updateBangumi();

    //获取首页推荐页banner
    @GET("http://app.bilibili.com/x/banner?plat=4&build=411007&channel=bilih5")
    Call<RecommendBannerBean> updateRecommendBanner();

    //获取首页番剧-新番完结分类
    @GET("http://bangumi.bilibili.com/api/app_index_page?_device=android")
    Call<BanguminIndexHeaderBean> updateBangumiIndexHeader();

}
