package pkg1_okhttp_code_test

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.junit.Test

/**
 * @description 举例: 日志拦截器
 * @date        2022/11/16 10:31 AM
 */
class InterceptorTest1 {

  @Test
  fun foo() {
    val okHttpClient = OkHttpClient.Builder()
      .addInterceptor(Interceptor { chain ->
        // httpLogInterceptor 自定义日志拦截器
        // 如果添加到application拦截器上的话，打印的是用户发出的请求
        // 自定义拦截器，在intercept方法中可以拿到链条对象
        val request = chain.request()
        val url = request.url.newBuilder()
          .addQueryParameter("name", "xxx")
          .build()
        // 注意: 自定义拦截器一定要执行proceed()方法，才能把请求交给下一个拦截器，否则链条会断开
        chain.proceed(request.newBuilder().url(url).build())
      })
      // 如果添加到network拦截器上的话，打印的是真正发出去的请求
      // .addNetworkInterceptor() // 经过OkHttp各种拦截器，如BridgeInterceptor桥接拦截器，会补全请求头数据
      .build()
  }
}