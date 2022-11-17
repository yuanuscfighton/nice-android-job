package com.laioffer.my_network.demo3_chain.interceptor

import com.laioffer.my_network.demo3_chain.Chain
import com.laioffer.my_network.demo3_chain.Interceptor

class BridgeInterceptor : Interceptor {
  override fun intercept(chain: Chain): String {
    println("开始执行桥接拦截器")
    val result = chain.proceed(chain.mRequest + "==>经过桥接拦截器");
    println("结束执行桥接拦截器")
    return "$result==>经过桥接拦截器";
  }

}
