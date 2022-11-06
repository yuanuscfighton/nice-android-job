package com.laioffer.my_jetpack.lesson1_lifecycle.demo5

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @description 地图定位功能的模拟 -- 观察者 Observer
 * @date        2022/11/3 7:28 上午
 */
class MyLocationListener : LifecycleObserver {

  private val TAG = "Demo4"

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun create() = Log.e(TAG, "create 正在启动系统定位服务中...")

  @OnLifecycleEvent(Lifecycle.Event.ON_START)
  fun start() = Log.e(TAG, "start 连接系统定位服务...")

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun resume() = Log.e(TAG, "resume 系统定位的界面展示...")

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun pause() = Log.e(TAG, "pause 系统定位的界面关闭...")

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  fun stop() = Log.e(TAG, "stop 断开系统定位服务...")

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  fun destroy() = Log.e(TAG, "destroy 正在停止系统定位服务...")
}