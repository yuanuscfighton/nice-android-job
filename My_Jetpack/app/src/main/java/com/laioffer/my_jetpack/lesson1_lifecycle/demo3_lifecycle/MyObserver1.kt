package com.laioffer.my_jetpack.lesson1_lifecycle.demo3_lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @description 可以有多个观察者。实现DefaultLifecycleObserver
 *              好处: 可以拿到Activity/Fragment的所有环境
 * @date        2022/11/2 10:30 下午
 */
class MyObserver1 : DefaultLifecycleObserver {

  private val TAG = MyObserver1::class.java.simpleName

  override fun onCreate(owner: LifecycleOwner) {
    super.onCreate(owner)
    Log.e(TAG, "onCreate()")
  }

  override fun onResume(owner: LifecycleOwner) {
    super.onResume(owner)
    Log.e(TAG, "onResume()")
  }
}