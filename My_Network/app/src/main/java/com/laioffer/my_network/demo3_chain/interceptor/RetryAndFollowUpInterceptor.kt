package com.laioffer.my_network.demo3_chain.interceptor

import com.laioffer.my_network.demo3_chain.Chain
import com.laioffer.my_network.demo3_chain.Interceptor

/**
 *  @description 定义该拦截器是index=0
 *               第一个拿到请求，最后一个拿到响应
 *  @date 2022/11/15 8:18 AM
 */
class RetryAndFollowUpInterceptor : Interceptor {

  /**
   * @param chain 此处拿到的链条对象是我们新创建的index=1的链条对象
   */
  override fun intercept(chain: Chain): String {
    // 本拦截器做的事情。即，在交给下一个拦截器之前，做一些自己的事情
    println("开始执行重试重定向拦截器")

    // 处理完成之后，把处理好的request交给index=1的链条对象去处理
    val result = chain.proceed(chain.mRequest + "==>经过重试重定向拦截器")

    // 获得结果后，加一些自己的东西
    println("结束执行重试重定向拦截器")

    return "$result==>经过重试重定向拦截器"
  }
}
