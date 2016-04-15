package cn.figo.mydemo.http;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import cn.figo.mydemo.utils.MD5;

/**
 * User: Ligx
 * Date: 2016-03-15
 * Time: 09:56
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class SignInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String sign = MD5.encryptMD5(request.httpUrl().encodedQuery()+"ea85624dfcf12d7cc7b2b3a94fac1f2c");
        request = request.newBuilder().url(request.httpUrl().newBuilder().addQueryParameter("sign", sign).build()).build();
        return chain.proceed(request);
    }
}
