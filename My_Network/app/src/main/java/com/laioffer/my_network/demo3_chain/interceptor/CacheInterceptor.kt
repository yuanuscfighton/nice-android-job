package com.laioffer.my_network.demo3_chain.interceptor;


import com.laioffer.my_network.demo3_chain.Chain
import com.laioffer.my_network.demo3_chain.Interceptor;

class CacheInterceptor : Interceptor {
  override fun intercept(chain: Chain): String {
    println("开始执行缓存拦截器");
    val result = chain.proceed(chain.mRequest + "==>经过缓存拦截器");
    println("结束执行缓存拦截器");
    return "$result==>经过缓存拦截器";
  }


}