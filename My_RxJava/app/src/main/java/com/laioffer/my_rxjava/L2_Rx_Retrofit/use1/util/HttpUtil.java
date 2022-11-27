package com.laioffer.my_rxjava.L2_Rx_Retrofit.use1.util;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {

  private static final String TAG = HttpUtil.class.getSimpleName();

  /**
   * 服务器域名设置为base_url
   */
  public final static String BASE_URL = "https://www.wanandroid.com/";

  /**
   * 根据各种配置创建出Retrofit
   *
   * @return 返回创建好的Retrofit
   */
  public static Retrofit getOnlineCookieRetrofit() {
    OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
    // 各种参数配置
    OkHttpClient okHttpClient = httpBuilder
        .addNetworkInterceptor(new StethoInterceptor())
        .readTimeout(10000, TimeUnit.SECONDS)
        .connectTimeout(10000, TimeUnit.SECONDS)
        .writeTimeout(10000, TimeUnit.SECONDS)
        .build();


    // Retrofit
    // (1) 使用OkHttp请求接口，i.e.，处理去的数据
    // (2) 使用RxJava处理服务器返回的数据
    return new Retrofit.Builder().baseUrl(BASE_URL)
        /** ******************** 去的数据: 请求用OkHttp *********************/
        .client(okHttpClient)

        /** ******************** 回来的数据: 使用RxJava处理 ******************/
        // Gson: 解析Json的工具，将json字符串解析成JavaBean对象
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        // RxJava处理工具
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
  }
}
