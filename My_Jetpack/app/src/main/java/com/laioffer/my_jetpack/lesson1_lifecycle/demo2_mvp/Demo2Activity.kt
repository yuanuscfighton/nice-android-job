package com.laioffer.my_mvx.lesson1_lifecycle.demo2_mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laioffer.my_mvx.R

/**
 * @description: 版本2: MVP，P层监听生命周期函数
 * @date: 2022/9/19 7:52 上午
 */
class Demo2Activity : AppCompatActivity() {

  private var mMyPresenter: MyPresenter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.empty_layout)

    mMyPresenter = MyPresenter()
  }

  override fun onResume() {
    super.onResume()
    mMyPresenter?.onResume()
  }

  override fun onPause() {
    super.onPause()
    mMyPresenter?.onPause()
  }
}