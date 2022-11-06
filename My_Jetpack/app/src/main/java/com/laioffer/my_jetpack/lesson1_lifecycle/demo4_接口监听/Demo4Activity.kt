package com.laioffer.my_jetpack.lesson1_lifecycle.demo4_接口监听

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @description 接口监听法
 * @date        2022/11/2 10:41 下午
 *
 * Demo4Activity是一个被观察者，且持有LifecycleOwner，专业名词:宿主，含义就是activity/fragment生命周期
 */
class Demo4Activity : AppCompatActivity() {

  private lateinit var myPresenter: IPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // 一般是有多个IPresenter实现类的
    myPresenter = MyPresenter()

    // 观察者与被观察者关联起来
    // 被观察者[Observable].addObserver(观察者[Observer])
    lifecycle.addObserver(myPresenter)
  }
}