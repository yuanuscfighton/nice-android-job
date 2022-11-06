package com.laioffer.my_jetpack.lesson1_lifecycle.demo3_lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @description 观察者，实现LifecycleObserver接口
 * @date        2022/11/2 10:29 下午
 */
class MyObserver : LifecycleObserver {

  private val TAG = MyObserver::class.java.simpleName

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun connectListener() = Log.e(TAG, "connectListener run ...")

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun disconnectListener() = Log.e(TAG, "disconnectListener run ...")
}