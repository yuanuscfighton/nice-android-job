package com.laioffer.my_network.demo3_chain.interceptor

import com.laioffer.my_network.demo3_chain.Chain
import com.laioffer.my_network.demo3_chain.Interceptor

class CallServerInterceptor : Interceptor {


  override fun intercept(chain: Chain): String {
    println("开始执行请求服务器拦截器")
    println("===发起请求===")
    println("结束执行请求服务器拦截器")
    return chain.mRequest + "==>经过请求服务器拦截器\nHttp响应==>经过请求服务器拦截器"
  }
}