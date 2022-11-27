package com.laioffer.my_rxjava.L3_source.demo1_观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 被观察者: 微信公众号的服务，用来发布消息
 * @date 2022/9/16 10:25 下午
 */
public class WeChatServerObservableImpl implements Observable {

  private final List<Observer> mObservers = new ArrayList<>();
  private String mMessage;

  @Override
  public void addObserver(Observer o) {
    mObservers.add(o);
  }

  @Override
  public void removeObserver(Observer o) {
    mObservers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (Observer o : mObservers) {
      o.update(mMessage);
    }
  }

  @Override
  public void pushMessage(String message) {
    mMessage = message;
    notifyObservers();
  }
}
