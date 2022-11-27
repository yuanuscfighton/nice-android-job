package com.laioffer.my_rxjava.L3_source.demo1_观察者模式;

import android.util.Log;

/**
 * @description 具体的观察者
 * @date 2022/9/17 10:55 上午
 */
public class Client implements Observer {

  private final String mName;

  public Client(String name) {
    mName = name;
  }

  @Override
  public void update(String message) {
    Log.e("[demo1_观察者模式]", mName + "收到了 [" + message + "]");
  }
}
