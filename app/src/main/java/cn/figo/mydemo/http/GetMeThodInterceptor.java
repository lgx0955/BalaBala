package cn.figo.mydemo.http;


import android.os.Looper;

import java.io.IOException;

import cn.figo.mydemo.bean.ResponseBodyBean;
import cn.figo.mydemo.utils.MD5;
import cn.figo.mydemo.utils.NetworkUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class GetMeThodInterceptor<T> implements Interceptor {
    CachePolicy cachePolicy;
    MyRetrofitCallBack myRetrofitCallBack;
    Call responseCall;

    public GetMeThodInterceptor(CachePolicy cachePolicy, MyRetrofitCallBack myRetrofitCallBack, Call responseCall) {
        this.cachePolicy = cachePolicy;
        this.myRetrofitCallBack = myRetrofitCallBack;
        this.responseCall = responseCall;
    }

    boolean isCacheOk = false;
    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request request = chain.request();
        myRetrofitCallBack.setRequest(request);

        ResponseBodyBean myresponse = null;
        myRetrofitCallBack.onBefore(request);

        if (request.method().equals("GET")) {
            //call之前返回缓存
            if (cachePolicy == CachePolicy.POLICY_CACHE_ONLY || cachePolicy == CachePolicy.POLICY_CACHE_AndRefresh || cachePolicy == CachePolicy.POLICY_BEFORE_AND_AFTER_NET) {
                myRetrofitCallBack.onUseCache(request);
                isCacheOk = true;
                if (cachePolicy == CachePolicy.POLICY_CACHE_ONLY) {
                    myRetrofitCallBack.setIsCacheOkf(true);
                    responseCall.cancel();
                    return null;
                }
            }
            myRetrofitCallBack.setIsCacheOkf(isCacheOk);
            // 当没有使用缓存或者缓存策略是网后更新
            if (!isCacheOk && cachePolicy == CachePolicy.POLICY_BEFORE_AND_AFTER_NET) {
                if (myresponse!=null) {
                    if (myresponse.getStatus() == 0) {
                        myRetrofitCallBack.onUseCache(request);
                    } else {
                        myRetrofitCallBack.onFail("网络错误");
                    }
                }
            }
        }

        boolean hasNet = NetworkUtils.isNetworkAvailable();
        Response response = null;
        if (hasNet) {
            try {
                response = chain.proceed(request);
            }catch (Exception e){}
        }else {
            new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    myRetrofitCallBack.onNoNet(request,isCacheOk, request.url().toString());
                    myRetrofitCallBack.onFail();
                }
            });
        }
        return response;
    }


    private static String buildKey(String url) {
        try {
            return	MD5.encryptMD5(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}