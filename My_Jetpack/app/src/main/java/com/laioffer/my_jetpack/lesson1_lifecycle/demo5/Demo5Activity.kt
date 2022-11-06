package com.laioffer.my_jetpack.lesson1_lifecycle.demo5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.my_jetpack.R

/**
 * @description 被观察者 Observable
 * @date        2022/11/2 10:38 下午
 */
class Demo5Activity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    lifecycle.addObserver(MyLocationListener())
  }


}