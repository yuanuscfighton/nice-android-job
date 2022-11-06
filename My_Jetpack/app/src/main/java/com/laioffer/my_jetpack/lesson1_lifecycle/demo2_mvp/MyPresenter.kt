package com.laioffer.my_mvx.lesson1_lifecycle.demo2_mvp

import android.util.Log

/**
 * @description: P层: P层需要把数据回传给Activity(UI)，如果此时Activity销毁了，就不应该回传数据给Activity了。
 *                因此P层需要监听Activity的声明周期
 * @date: 2022/9/19 7:55 上午
 */
class MyPresenter {

  private val TAG = MyPresenter::class.java.simpleName

  fun onResume() = Log.e(TAG, "onResume invoked ...")

  fun onPause() = Log.e(TAG, "onPause invoked ...")
}