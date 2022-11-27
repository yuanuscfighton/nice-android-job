package com.laioffer.my_rxjava.L2_Rx_Retrofit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @description Rx工具类
 * @date 2022/10/6 9:47 下午
 */
public class RxUtils {

  public static <UD> ObservableTransformer<UD, UD> rxud() {
    return new ObservableTransformer<UD, UD>() {
      @Override
      public ObservableSource<UD> apply(Observable<UD> upstream) {
        return upstream
            .subscribeOn(Schedulers.io()) // 给上面代码分配异步线程
            .observeOn(AndroidSchedulers.mainThread()) // 给下面代码分配主线程;
            .map(new Function<UD, UD>() {
              @Override
              public UD apply(UD ud) {
                return ud;
              }
            });
        // .....
      }
    };
  }
}
