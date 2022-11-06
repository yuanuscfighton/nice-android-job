package com.laioffer.my_jetpack.lesson1_lifecycle.tools

import android.util.Log

/**
 * @description lifecycle工具类
 * @date        2022/10/31 10:54 下午
 */
object Utils {

  private const val TAG = "[l1-lifecycle] "

  fun logMessage(tag: String, message: String) {
    Log.e(TAG, tag + message)
  }
}