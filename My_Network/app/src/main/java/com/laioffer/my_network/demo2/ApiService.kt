package com.laioffer.my_network.demo2

import retrofit2.Call
import retrofit2.http.GET

/**
 * @description 接口
 * @date        2022/11/6 11:57 AM
 */
interface ApiService {

  // GET注解表示，当调用getData()方法的时候，会发送一条GET请求，请求的地址就是我们在GET注解中传入的具体参数
  // (这里只需要传入请求地址的相对路径即可)
  @GET("xxxx")
  fun getData(): Call<List<Data>>
}