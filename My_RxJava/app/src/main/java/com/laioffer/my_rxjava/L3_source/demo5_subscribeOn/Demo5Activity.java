package com.laioffer.my_rxjava.L3_source.demo5_subscribeOn;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @description: subscribeOn源码分析
 * @date: 2022/9/25 3:50 下午
 */
public class Demo5Activity extends AppCompatActivity {

  public static final String TAG = Demo5Activity.class.getSimpleName();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
      @Override
      public Scheduler apply(Scheduler scheduler) throws Throwable {
        Log.e(TAG, "apply: 全局监听scheduler: " + scheduler);
        return scheduler;
      }
    });

    RxJavaPlugins.setInitIoSchedulerHandler(new Function<Supplier<Scheduler>, Scheduler>() {
      @Override
      public Scheduler apply(Supplier<Scheduler> schedulerSupplier) throws Throwable {
        Log.e(TAG, "apply: 全局监听init scheduler:" + schedulerSupplier.get());
        return schedulerSupplier.get();
      }
    });


    Observable.create(
            // 自定义source
            new ObservableOnSubscribe<String>() {
              @Override
              public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("SubscribeOn源码分析");
              }
            })

        // 给上面的代码分配线程
        .subscribeOn(Schedulers.io())

        .subscribe(
            // 终点
            new Observer<String>() {
              @Override
              public void onSubscribe(@NonNull Disposable d) {

              }

              @Override
              public void onNext(@NonNull String s) {

              }

              @Override
              public void onError(@NonNull Throwable e) {

              }

              @Override
              public void onComplete() {

              }
            });
  }
}
