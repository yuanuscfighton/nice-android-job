package com.laioffer.my_network.demo1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.my_network.R
import com.laioffer.my_network.demo2_okhttp_code.okhttp3.FormBody
import com.laioffer.my_network.demo2_okhttp_code.okhttp3.OkHttpClient
import okhttp3.Request

/**
 * @description okhttp网络请求
 * @date        2022/11/6 11:16 AM
 */
class Demo1Activity : AppCompatActivity() {

  private val URL = "https://www.baidu.com"
  lateinit var content: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    content = findViewById(R.id.demo1_content)
    sendGetRequest()
    sendPostRequest()
  }

  /**
   * GET请求
   */
  private fun sendGetRequest() {
    // step1 创建OkHttpClient的实例
    val client = OkHttpClient()

    // step2 创建Request对象
    val request = Request.Builder()
      .url(URL)
      .build()

    // step3 创建Call对象，并调用execute()方法
    // (1) 调用OkHttpClient的newCall()方法来创建Call对象
    // (2) 调用execute()方法来发送请求并获取服务器返回的数据
    // (3) Response对象就是服务器返回的数据
    val response = client.newCall(request).execute()

    // step4 得到返回的具体内容
    val responseData = response.body?.string()

    // response.isSuccessful

  }


  /**
   * POST请求
   */
  private fun sendPostRequest() {
    // step1 构建Request Body对象，存放待提交的参数
    val requestBody = FormBody.Builder()
      .add("username", "小明")
      .add("password", "123456")
      .build()

    // step2 创建OkHttpClient的实例
    val client = OkHttpClient()

    // step3 创建Request对象
    val request = Request.Builder()
      .url(URL)
      .post(requestBody)
      .build()

    // step4 创建Call对象，并调用execute()方法
    // (1) 调用OkHttpClient的newCall()方法来创建Call对象
    // (2) 调用execute()方法来发送请求并获取服务器返回的数据
    // (3) Response对象就是服务器返回的数据
    val response = client.newCall(request).execute()

    // step5 得到返回的具体内容
    val responseBody = response.body?.toString()
  }
}