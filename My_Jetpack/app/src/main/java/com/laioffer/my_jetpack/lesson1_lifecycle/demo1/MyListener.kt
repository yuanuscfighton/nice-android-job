package com.laioffer.my_jetpack.lesson1_lifecycle.demo1

import com.laioffer.my_jetpack.lesson1_lifecycle.tools.Utils.logMessage

/**
 * @description 监听器
 * @date        2022/10/31 11:05 下午
 */
class MyListener {

  private val TAG = "[MyListener] "

  fun start() = logMessage(TAG, "start invoked ...")

  fun stop() = logMessage(TAG, "stop invoked ...")
}