package com.laioffer.my_jetpack.lesson1_lifecycle.demo3_lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.my_jetpack.R

/**
 * @description 使用LifecycleOwner和LifecycleObserver实现
 * @date        2022/11/2 10:27 下午
 */
class Demo3Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    lifecycle.addObserver(MyObserver())
    lifecycle.addObserver(MyObserver1())
  }
}