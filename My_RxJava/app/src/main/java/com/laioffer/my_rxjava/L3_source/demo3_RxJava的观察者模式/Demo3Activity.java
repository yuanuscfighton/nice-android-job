package com.laioffer.my_rxjava.L3_source.demo3_RxJava的观察者模式;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @description RxJava的观察者模式
 * @date 2022/9/17 5:16 下午
 * <p>
 * 第1步: 分析Observer的源码
 * 第2步: 分析Observable创建过程
 * 第3步: 分析subscribe订阅过程
 */
public class Demo3Activity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // 第2步: 分析Observable创建过程
    // 进入create()方法: new ObservableCreate<>(source)
    //    (i) 创建了ObservableCreate对象;
    //    (ii) 把我们自己定义的source传入ObservableCreate中
    Observable.create(
            // new ObservableOnSubscribe<String>(): 自定义source
            new ObservableOnSubscribe<String>() {
              // 这个重写的方法，在ObservableCreate#subscribeActual()中被调用source.subscribe(parent);
              // source就是我们自定义的source，parent是发射器
              @Override
              public void subscribe(@NonNull ObservableEmitter<String> emitter) {
                // emitter(发射器).onNext()，面向发射器编程，发射器具体怎么做不管。发射器里面会用自定义观察者(observer.onNext())
                emitter.onNext("这是Observable的创建过程");
              }
            })


        // 第3步: 分析subscribe订阅过程
        // 这里是上面创建的ObservableCreate对象调用的subscribe，相当于 ObservableCreate.subscribe(Observer)
        // 因为Observable#create()会返回ObservableCreate对象
        .subscribe( // 在subscribe()方法里，会调用subscribeActual()方法，而subscribeActual()是抽象方法，具体实现是在ObservableCreate类中


            // 第1步: 分析Observer的源码
            // 自定义观察者，实现Observer接口
            new Observer<String>() {

              // onSubscribe()方法执行时机: 一订阅的时候
              @Override
              public void onSubscribe(@NonNull Disposable d) {
                /**
                 * 当一订阅的时候，就会调用这个方法
                 * 因为:
                 * 在 {@link io.reactivex.rxjava3.internal.operators.observable.ObservableCreate#subscribeActual(Observer)}方法中，
                 * CreateEmitter<T> parent = new CreateEmitter<>(observer);
                 * observer.onSubscribe(parent);
                 */
              }

              // 拿到上一个卡片流下来的数据
              @Override
              public void onNext(@NonNull String s) {

              }

              @Override
              public void onError(@NonNull Throwable e) {

              }

              @Override
              public void onComplete() {

              }
            }
        );
  }
}
