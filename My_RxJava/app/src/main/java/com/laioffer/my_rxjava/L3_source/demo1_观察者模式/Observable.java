package com.laioffer.my_rxjava.L3_source.demo1_观察者模式;

/**
 * @description 被观察者
 * @date 2022/9/15 9:49 下午
 */
public interface Observable {

  void addObserver(Observer o);

  void removeObserver(Observer o);

  void notifyObservers();

  // 微信公众号的服务，发布一条消息
  void pushMessage(String message);
}
