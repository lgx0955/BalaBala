package cn.figo.mydemo.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * User: Ligx
 * Date: 2015-10-15
 * Time: 15:52
 * Copyright (c)Ligx All rights reserved.
 */
public class RetrofitClientManager {

    private static final String TAG = "RetrofitClientManager";
    private static RetrofitClientManager mInstance;
    public static Retrofit retrofit;
    public static String baseurl="http://app.bilibili.com/api/index/";
    private static  CachePolicy cachePolicy;
    public CachePolicy getCachePolicy() {
        return cachePolicy;
    }
    public void setCachePolicy(CachePolicy cachePolicy) {
        this.cachePolicy = cachePolicy;
    }


    public static Api api;
    static {
        api = RetrofitClientManager.getRetrofit().create(Api.class);
    }

    public RetrofitClientManager() {

    }

    public static RetrofitClientManager getInstance() {
        if (mInstance == null)
        {
            synchronized (RetrofitClientManager.class)
            {
                if (mInstance == null)
                {
                    mInstance = new RetrofitClientManager();
                }
            }
        }
        return mInstance;
    }

    public static OkHttpClient httpClient;
    public static Retrofit getRetrofit() {
        if (retrofit == null){
            httpClient = new OkHttpClient();
//            httpClient.setReadTimeout(15, TimeUnit.SECONDS);
//            httpClient.setConnectTimeout(15, TimeUnit.SECONDS);
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }

//    public static <T> void getAsyn(Call<T> responseCall, MyRetrofitCallBack myRetrofitCallBack) {
//        cachePolicy = myRetrofitCallBack.getCachePolicy();
//        retrofit.client().interceptors().clear();
//        retrofit.client().interceptors().add(new GetMeThodInterceptor(cachePolicy, myRetrofitCallBack,responseCall));
//        responseCall.enqueue(myRetrofitCallBack);
//    }
//
//    public static <T> void getSignAsyn(Call<T> responseCall, MyRetrofitCallBack myRetrofitCallBack) {
//        cachePolicy = myRetrofitCallBack.getCachePolicy();
//        retrofit.client().interceptors().clear();
//        retrofit.client().interceptors().add(new GetMeThodInterceptor(cachePolicy, myRetrofitCallBack,responseCall));
//        retrofit.client().interceptors().add(new SignInterceptor());
//        responseCall.enqueue(myRetrofitCallBack);
//    }
//
//    public static <T> void getAsyn(Call<T> responseCall, Callback callback) {
//        responseCall.enqueue(callback);
//    }
}
