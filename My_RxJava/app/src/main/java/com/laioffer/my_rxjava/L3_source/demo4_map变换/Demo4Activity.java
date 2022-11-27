package com.laioffer.my_rxjava.L3_source.demo4_map变换;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

/**
 * @description: map变换，源码分析
 * @date: 2022/9/24 8:28 下午
 */
public class Demo4Activity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    executeTask();
  }

  private void executeTask() {

    // Observable.create() 返回ObservableCreate对象
    Observable.create(new ObservableOnSubscribe<String>() {
          @Override
          public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {

          }
        })

        .map(new Function<String, Bitmap>() {
          @Override
          public Bitmap apply(String s) {
            return null;
          }
        })

        .subscribe(new Observer<Bitmap>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {

          }

          @Override
          public void onNext(@NonNull Bitmap bitmap) {

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
