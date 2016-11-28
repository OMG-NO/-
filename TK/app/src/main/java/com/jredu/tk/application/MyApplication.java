package com.jredu.tk.application;

import android.app.Application;

/**
 * Created by Administrator on 2016/11/2.
 */
public class MyApplication extends Application {
   /*@Override
    public void onCreate()
    {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                        //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }*/
}
