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
        String sign2 = "cid=11627628&from=miniplay&player=11c15888dc316e05a15fdd0a02ed6584f";

        try {
            System.out.println("======sign2   "+new String(MD5.encryptMD5(sign2.getBytes()),"utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request = request.newBuilder().url(request.httpUrl().newBuilder().addQueryParameter("sign", sign).build()).build();
        return chain.proceed(request);
    }
}
