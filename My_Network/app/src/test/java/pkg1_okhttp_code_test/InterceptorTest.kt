package pkg1_okhttp_code_test

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.junit.Test

/**
 * @description 自定义拦截器
 * @date        2022/11/16 10:31 AM
 */
class InterceptorTest {

  @Test
  fun foo() {
    val okHttpClient = OkHttpClient.Builder()
      .addInterceptor(object : Interceptor {
        // 自定义拦截器，在intercept方法中可以拿到链条对象
        override fun intercept(chain: Interceptor.Chain): Response {
          val request = chain.request()
          val url = request.url.newBuilder()
            .addQueryParameter("name", "xxx")
            .build()
          // 注意: 自定义拦截器一定要执行proceed()方法，才能把请求交给下一个拦截器，否则链条会断开
          return chain.proceed(request.newBuilder().url(url).build())
        }
      })
  }
}