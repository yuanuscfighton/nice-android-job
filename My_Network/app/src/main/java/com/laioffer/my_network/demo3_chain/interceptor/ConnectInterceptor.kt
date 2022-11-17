package com.laioffer.my_network.demo3_chain.interceptor

import com.laioffer.my_network.demo3_chain.Chain
import com.laioffer.my_network.demo3_chain.Interceptor

class ConnectInterceptor : Interceptor {

  override fun intercept(chain: Chain): String {
    println("开始执行连接拦截器")
    val result = chain.proceed(chain.mRequest + "==>经过连接拦截器")
    println("结束执行连接拦截器")
    return "$result==>经过连接拦截器"
  }
}