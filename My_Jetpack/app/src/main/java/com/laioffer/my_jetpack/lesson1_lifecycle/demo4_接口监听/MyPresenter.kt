package com.laioffer.my_jetpack.lesson1_lifecycle.demo4_接口监听

import com.laioffer.my_jetpack.lesson1_lifecycle.tools.Utils

/**
 * @description 接口实现类
 * @date        2022/11/2 10:42 下午
 */
class MyPresenter : IPresenter {

  private val TAG = "[demo4][MyPresenter]"

  override fun onResume() {
    Utils.logMessage(TAG, "onResume invoked...")
  }

  override fun onPause() {
    Utils.logMessage(TAG, "onPause invoked...")
  }
}