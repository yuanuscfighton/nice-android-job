package com.laioffer.my_rxjava.L3_source.demo1_观察者模式;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.my_rxjava.R;

public class Demo1Activity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    test();
  }

  private void test() {
    String msg = "学习kotlin";

    // 创建一个微信公众号的服务(被观察者)
    Observable wechatServer = new WeChatServerObservableImpl();

    // 创建用户(观察者) -- 多个
    Observer user1 = new Client("张三");
    Observer user2 = new Client("李四");
    Observer user3 = new Client("王五");

    // 订阅: 格式: 被观察者.订阅(观察者)
    wechatServer.addObserver(user1);
    wechatServer.addObserver(user2);
    wechatServer.addObserver(user3);

    // 推送消息了
    wechatServer.pushMessage(msg);
  }
}