package com.laioffer.my_rxjava.L3_source.demo2_Rx_Hook;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/**
 * @description RxJava Hook点。即，对整个RxJava做监听
 * @date 2022/9/17 1:17 下午
 */
public class Demo2Activity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // RxJavaPlugins.onAssembly(new Observable<>(source)) -- 绝大部分的操作符都有RxJavPlugins这个hook
    //noinspection rawtypes
    RxJavaPlugins.setOnObservableAssembly(new Function<Observable, Observable>() {
      @SuppressWarnings("rawtypes")
      @Override
      public Observable apply(Observable observable) {
        Log.e("[demo2_RxJava_Hook]", "apply: 整个项目 全局监听 到底有多少个地方使用RxJava: " + observable);
        return observable;
      }
    });

    testHook();
  }

  private void testHook() {
    //noinspection ResultOfMethodCallIgnored
    Observable.create(new ObservableOnSubscribe<Object>() {
          @Override
          public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
            emitter.onNext("A");
          }
        })
        .map(new Function<Object, Boolean>() {
          @Override
          public Boolean apply(Object o) throws Throwable {
            return true;
          }
        })
        .subscribe(new Consumer<Boolean>() {
          @Override
          public void accept(Boolean aBoolean) throws Throwable {

          }
        });
  }
}
