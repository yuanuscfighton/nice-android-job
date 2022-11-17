package com.laioffer.my_network.demo3_chain

import com.laioffer.my_network.demo3_chain.interceptor.BridgeInterceptor
import com.laioffer.my_network.demo3_chain.interceptor.CacheInterceptor
import com.laioffer.my_network.demo3_chain.interceptor.CallServerInterceptor
import com.laioffer.my_network.demo3_chain.interceptor.ConnectInterceptor
import com.laioffer.my_network.demo3_chain.interceptor.RetryAndFollowUpInterceptor

fun main() {
  val interceptors = ArrayList<Interceptor>()
  interceptors.add(RetryAndFollowUpInterceptor())
  interceptors.add(BridgeInterceptor())
  interceptors.add(CacheInterceptor())
  interceptors.add(ConnectInterceptor())
  interceptors.add(CallServerInterceptor())

  // 链条对象
  val chain = Chain(interceptors, 0)
  println(chain.proceed("Http请求"))

  /**
   * 责任链模式开发步骤
   * step1 Interceptor接口
   * step2 具体拦截器实现Interceptor接口
   * step3 链条类: Chain
   * step4 测试类: Client
   */
}