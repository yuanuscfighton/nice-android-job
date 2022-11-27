package com.laioffer.my_rxjava.L1_使用.use1_开始;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @description 【RxJava思维编程】版本1: 最基本的使用
 * @date 2022/11/19 10:15 AM
 */
public class RxUse1Activity extends AppCompatActivity {

  private final static String PATH = "http://pic1.win4000.com/wallpaper/c/53cdd1f7c1f21.jpg";

  public void download() {
    // 1.起点(Observable，被观察者)
    Observable.just(PATH) // 分发事件，事件就是PATH(图片的地址)，这个事件是String类型。

        // 3.subscribe: 将「起点」和「终点」订阅起来
        .subscribe(

            // 2.终点(Observer，观察者)
            new Observer<String>() {

              // 订阅的开始
              @Override
              public void onSubscribe(Disposable d) {

              }

              // 拿到事件
              @Override
              public void onNext(String s) {

              }

              // 错误事件
              @Override
              public void onError(Throwable e) {

              }

              // 完成事件
              @Override
              public void onComplete() {

              }
            });
  }
}
