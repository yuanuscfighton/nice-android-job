package com.laioffer.my_rxjava.L3_source.demo1_观察者模式;

/**
 * @description 观察者
 * @date 2022/9/16 9:36 下午
 */
public interface Observer {

  // 当被观察者发生变化了，观察者可以知道
  void update(String message);
}
