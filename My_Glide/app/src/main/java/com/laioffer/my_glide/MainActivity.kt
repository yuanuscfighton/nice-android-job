package com.laioffer.my_glide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.my_glide.glide.Glide

// Fresco  LoadImage  框架  看起来非常舒服   Git

// Glide 开了一周Glide 源码，找不到 网络请求的地方  研究了半年才能给大家说出一点东西
// Git  视频第一帧  功能性

// RxJava  OkHtt

/**
 * 想问一下，当在activity onResume的时候,页面控件点击执行glide加载图片的时候,此时activity走的函数已经到了onResume,此时加入的空白Fragment的onCreate如何跟activity同步呢？如何触发Fragment的onCreate监听呢？
 */

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    Glide.with(this)
  }

  override fun onStart() {
    super.onStart()
  }
}