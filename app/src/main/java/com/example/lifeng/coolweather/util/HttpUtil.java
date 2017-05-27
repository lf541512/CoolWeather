package com.example.lifeng.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lifeng on 2017/5/27 0027.
 */

public class HttpUtil {
    public static void sendOkHttpReq(String address,okhttp3.Callback cb){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(cb);
    }
}
