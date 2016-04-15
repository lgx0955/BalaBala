package cn.figo.mydemo.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.squareup.okhttp.Request;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import cn.figo.mydemo.R;
import cn.figo.mydemo.app.MyApplication;
import cn.figo.mydemo.bean.ResponseBaseBean;
import cn.figo.mydemo.bean.ResponseBodyBean;
import cn.figo.mydemo.utils.MD5;
import cn.figo.mydemo.utils.ToastHelper;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * User: Ligx
 * Date: 2015-10-15
 * Time: 10:02
 * Copyright (c)Ligx All rights reserved.
 */
public class MyRetrofitCallBack<T extends ResponseBaseBean>  implements Callback<T> {

    private Context mContext;
    private CachePolicy cachePolicy;
    boolean isCacheOkf = false;
    Long existTime=-1l;//缓存存活时间

    Handler handler = new Handler(Looper.getMainLooper());

    Request request;

    public MyRetrofitCallBack(Context mContext, CachePolicy cachePolicy) {
        this.mContext = mContext;
        this.cachePolicy = cachePolicy;
    }

    public MyRetrofitCallBack(Context mContext, CachePolicy cachePolicy, Long existTime) {
        this.mContext = mContext;
        this.cachePolicy = cachePolicy;
        this.existTime = existTime;
    }


    public CachePolicy getCachePolicy() {
        return cachePolicy;
    }

    public void setCachePolicy(CachePolicy cachePolicy) {
        this.cachePolicy = cachePolicy;
    }

    public boolean isCacheOkf() {
        return isCacheOkf;
    }

    public void setIsCacheOkf(boolean isCacheOkf) {
        this.isCacheOkf = isCacheOkf;
    }

    public Long getExistTime() {
        return existTime;
    }

    public void setExistTime(Long existTime) {
        this.existTime = existTime;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
    static {
        gsonExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Expose
    static Gson gsonExpose;

    Gson gson = new Gson();

    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {
        if (mContext != null) {
            if (response.isSuccess()){
                final T t = response.body();
                int status = t.getCode();
                if (status == 0) {
                    //没有错误 需要保存缓存
                    if (cachePolicy != CachePolicy.POLICY_NOCACHE) {
                        MyApplication.liteOrmDb.delete(WhereBuilder.create(Cache.class).equals(Cache.COL_KEY, buildKey(response.raw().request().url().toString())));
                        Cache cache=new Cache();
                        cache.setKey(buildKey(response.raw().request().url().toString()));
                        cache.setResult(t.toString());
                        cache.setUpdateTime(System.currentTimeMillis());
                        //存活时间 默认－1无限时
                        if (existTime!=-1l)
                            cache.setExpireTime(System.currentTimeMillis() + existTime);
                        else
                            cache.setExpireTime(-1l);
                        MyApplication.liteOrmDb.save(cache);
<<<<<<< HEAD
                    }   
=======
                    }         
>>>>>>> release/1.0.3
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onDo(t);
                        }
                    });
                }else {
                    onFail("网络返回数据错误");
                }
            }else{
                int statusCode = response.code();
                try {
                    if (statusCode == 0) {
                        ToastHelper.showToast(mContext.getResources().getString(R.string.error_http_fail_connet_server), mContext);
                    } else if (statusCode >= 400) {
                        ToastHelper.showToast(mContext.getResources().getString(R.string.error_http_request),mContext);
                    } else if (statusCode >= 500) {
                        ToastHelper.showToast(mContext.getResources().getString(R.string.error_http_server_error), mContext);
                    } else {
                        ToastHelper.showToast(mContext.getResources().getString(R.string.error_http_server_busy), mContext);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                onFail();
            }
            onFinish();
        }
    }


    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        try {
            onNetError(request, t, isCacheOkf);
        }catch (Exception e){}

    }

    private String buildKey(String url) {
        try {
            return	MD5.encryptMD5(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }



    public void onBefore(Request request) {

    }

    public void onDo(T response) {

    }

    public void onFail() {

    }
    public void onFail(String info) {
        ToastHelper.showToast(info,mContext);
    }

    public void onFinish() {

    }

    /**
     * 处理网路异常
     *
     * @param t
     */
    public void onNetError(Request request, Throwable t, Boolean hasUserCache) {
        if (mContext!=null) {
            boolean isFromCache = false;
            if (cachePolicy == CachePolicy.POLICY_ON_NET_ERROR) {
                onUseCache(request);
            }

            // 同时提示网络问题
            String errorjson;
            if (isFromCache) {
                onFail("当前网络信号不好,使用缓存数据");
            } else {
                onFail("当前网络信号不好");

            }
            onFinish();
        }
    }


    /**
     * 使用缓存
     *
     * @param
     */
    public void onUseCache(Request request) {
        List<Cache> cache = MyApplication.liteOrmDb.query(QueryBuilder.create(Cache.class).whereEquals(Cache.COL_KEY, buildKey(request.url().toString())));
        if (cache != null && cache.size() > 0) {
            //判断缓存是否过期  -1无限时
            if (cache.get(0).getExpireTime()>System.currentTimeMillis()||cache.get(0).getExpireTime()==-1) {
                final T data = gson.fromJson(cache.get(0).getResult(), ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
                final ResponseBodyBean<T> response = new ResponseBodyBean<>();
                response.setData(data);
                response.setIsCache(true);
                try {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onDo(data);
                        }
                    });

                    isCacheOkf = true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (cachePolicy == CachePolicy.POLICY_CACHE_ONLY) {
                return;
            }
        } else {
            // 同时提示没有缓存
            onFail("没有缓存数据");
            onFinish();
        }
    }

    /**
     * 在没有网的时候的处理
     */
    public void onNoNet(Request request, Boolean hasUserCache, String url) {
        if (mContext != null) {
            if (getCachePolicy() == CachePolicy.POLICY_ON_NET_ERROR) {
                try {
                    Cache cache = MyApplication.liteOrmDb.<Cache>query(QueryBuilder.create(Cache.class).whereEquals(Cache.COL_KEY, buildKey(url))).get(0);
                    if (cache != null) {
                        onUseCache(request);
                    }
                }catch (Exception e){e.printStackTrace();}
            }
            onFail("没有可用网路");
            onFinish();
        }
    }
}
