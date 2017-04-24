package com.example.androidclass.androidclass;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 29185 on 2017/4/21.
 */

public class HttpUtil {
    public static void sendRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
