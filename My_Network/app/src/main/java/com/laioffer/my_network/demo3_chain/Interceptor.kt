package com.laioffer.my_network.demo3_chain

/**
 * @description 拦截器的接口，每个具体的拦截器都需要实现该接口，需要将这些拦截器组成一个链条
 * @date        2022/11/15 11:46 PM
 */
interface Interceptor {
  fun intercept(chain: Chain): String
}