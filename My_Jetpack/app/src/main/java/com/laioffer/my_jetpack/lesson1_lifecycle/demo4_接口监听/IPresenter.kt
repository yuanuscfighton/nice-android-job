package com.laioffer.my_jetpack.lesson1_lifecycle.demo4_接口监听

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @description 接口继承LivecycleOwner接口
 * @date        2022/11/2 10:41 下午
 */
interface IPresenter : LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun onResume()

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun onPause()
}