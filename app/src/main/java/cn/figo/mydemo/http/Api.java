package cn.figo.mydemo.http;

import cn.figo.mydemo.bean.AidBean;
import cn.figo.mydemo.bean.BangumiDetailBean;
import cn.figo.mydemo.bean.BangumiIndexBean;
import cn.figo.mydemo.bean.BanguminIndexHeaderBean;
import cn.figo.mydemo.bean.LiveIndexBean;
import cn.figo.mydemo.bean.LiveVideoSourceBean;
import cn.figo.mydemo.bean.RecommendBannerBean;
import cn.figo.mydemo.bean.VideoDetailBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * User: Ligx
 * Date: 2016-01-12
 * Time: 10:05
 * Copyright (c)Ligx All rights reserved.
 */
public interface Api {

    @Headers({
            "Content-type: application/json"
    })



    //获取首页番剧
    @GET("http://app.bilibili.com/bangumi/operation_module?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&module=bangumi&platform=android&screen=xxhdpi")
    Call<BangumiIndexBean> updateBangumi();

    //获取首页推荐页banner
    @GET("http://app.bilibili.com/x/banner?plat=4&build=411007&channel=bilih5")
    Call<RecommendBannerBean> updateRecommendBanner();

    //获取首页番剧-新番完结分类
    @GET("http://bangumi.bilibili.com/api/app_index_page?_device=android")
    Call<BanguminIndexHeaderBean> updateBangumiIndexHeader();

    //获取首页直播Banner 分类 列表
    @GET("http://live.bilibili.com/AppIndex/home?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&access_key=563d6046f06289cbdcb472601ce5a761&appkey=c1b107428d337928&build=410000&platform=android&scale=xxhdpi&sign=fbdcfe141853f7e2c84c4d401f6a8758")
    Call<LiveIndexBean> updateLiveIndex();

    //获取直播地址
    @GET("http://live.bilibili.com/api/playurl?platform=h5")
    Call<LiveVideoSourceBean> getLiveVideoSource(@Query("cid") String room_id);

    //获取番局详情
    @GET("http://bangumi.bilibili.com/api/season_v2?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&access_key=563d6046f06289cbdcb472601ce5a761&appkey=c1b107428d337928&build=412001&platform=android&season_id=3120&ts=1457685071000&type=bangumi&sign=2986520e3b93a03632003bc909a77f7f")
    Call<VideoDetailBean> getBangumiDetail();

    //获取视频弹幕
    @GET("http://comment.bilibili.com/{danmaku_id}.xml")
    Call<ResponseBody> getDanmaku(@Path("danmaku_id") String danmaku_id);

//    //获取视频弹幕
//    @GET("http:///{danmakuId}")
//    Call<ResponseBody> getDanmakuPath(@Path(value = "danmakuId") String danmakuId);

    //获取视频地址
    @GET("http://www.bilibili.com/m/html5")
    Call<AidBean> getVideoPath(@Query("aid") String aid, @Query("page") String page);


    //获取番剧的瀑布流详情
    @GET("http://bangumi.bilibili.com/api/season_v2?_device=android&_hwid=51e96f5f2f54d5f9&appkey=c1b107428d337928&build=412001&platform=android")
    Call<BangumiDetailBean> getBangumiDetailBySpId(@Query("sp_id") String sp_id,@Query("ts") String ts,@Query("type") String type);//sp

    //获取番剧lastupdate的详情
    @GET("http://bangumi.bilibili.com/api/season_v2?_device=android&_hwid=ac538400c68784bb&_ulv=10000&access_key=946bb9a10cbfda5fadc9ed818b5186f5&appkey=c1b107428d337928&build=412001&platform=android")
    Call<BangumiDetailBean> getBangumiDetailBySeasonID(@Query("season_id") String season_id,@Query("ts") String ts,@Query("type") String type);//bangumi


    //获取番剧播放地址
    @GET("http://api.bilibili.com/playurl?page=1&platform=html5&quality=1&vtype=mp4&type=jsonp&_=1476304264934")
    Call<String> getBangumiPlayUrl(@Query("aid") String aid);

}
