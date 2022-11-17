package com.laioffer.my_network.demo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.laioffer.my_network.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

/**
 * @description Retrofit的使用
 * @date        2022/11/6 11:56 AM
 */
class Demo2Activity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    requestData()
  }

  private fun requestData() {
    val retrofit: Retrofit = Retrofit.Builder()
      .baseUrl("www.baidu.com")
      .build()

    val api: ApiService = retrofit.create(ApiService::class.java)
    val task: Call<List<Data>> = api.getData()
    task.enqueue(object : Callback<List<Data>> {
      override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
        if (response.code() == HttpURLConnection.HTTP_OK) {
          try {
            val result: String = response.body().toString()
            val gson: Gson = Gson()
            val data: Data = gson.fromJson(result, Data::class.java)
          } catch (e: Exception) {

          }
        }
      }

      override fun onFailure(call: Call<List<Data>>, t: Throwable) {
        TODO("Not yet implemented")
      }

    })

  }

  private fun requestData1() {
    val retrofit: Retrofit = Retrofit.Builder()
      .baseUrl("www.baidu.com")
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    val api: ApiService = retrofit.create(ApiService::class.java)
    val task: Call<List<Data>> = api.getData()
    task.enqueue(object : Callback<List<Data>> {
      override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
        if (response.code() == HttpURLConnection.HTTP_OK) {
          try {
            val result: List<Data> = response.body()!!
          } catch (e: Exception) {

          }
        }
      }

      override fun onFailure(call: Call<List<Data>>, t: Throwable) {
        TODO("Not yet implemented")
      }

    })

  }
}