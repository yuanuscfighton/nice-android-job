package com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.client.v2_改进;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.my_rxjava.R;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.api.IRequestNetwork;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.api.LoginRequest;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.bean.LoginResponse;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.MyRetrofit;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.api.RegisterRequest;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.bean.RegisterResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @description 一行代码实现需求
 * @date 2022/9/4 6:46 下午
 */
public class RequestDemo2Activity extends AppCompatActivity {

  private ProgressDialog progressDialog;
  Disposable disposable;

  private TextView tv_register_ui;
  private TextView tv_login_ui;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_request);

    tv_register_ui = findViewById(R.id.tv_login_ui);
    tv_login_ui = findViewById(R.id.tv_login_ui);

  }

  public void request(View view) {

    /**
     * 一行代码 实现需求
     * 需求:
     * 还有弹出加载
     * * 1.请求服务器注册操作
     * * 2.注册完成之后，更新注册UI
     * * 3.马上去登录服务器操作
     * * 4.登录完成之后，更新登录的UI
     */
    MyRetrofit.createRetrofit().create(IRequestNetwork.class)
        // 1.请求服务器注册操作
        .registerAction(new RegisterRequest())
        .subscribeOn(Schedulers.io()) // 给上面 异步
        .observeOn(AndroidSchedulers.mainThread()) // 给下面分配主线程
        .doOnNext(new Consumer<RegisterResponse>() {
          @Override
          public void accept(RegisterResponse registerResponse) {
            // 2.注册完成之后，更新注册UI
          }
        })
        // 3.马上去登录服务器操作
        .observeOn(Schedulers.io()) // 给下面分配异步线程
        .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
          @Override
          public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) {
            return MyRetrofit.createRetrofit().create(IRequestNetwork.class)
                .loginAction(new LoginRequest());
          }
        })
        .observeOn(AndroidSchedulers.mainThread()) // 给下面 执行主线程
        .subscribe(new Observer<LoginResponse>() {

          // 一定是主线程，为什么，因为 subscribe 马上调用onSubscribe
          @Override
          public void onSubscribe(Disposable d) {
            progressDialog = new ProgressDialog(RequestDemo2Activity.this);
            progressDialog.show();
            // UI 操作
            disposable = d;
          }

          @Override
          public void onNext(LoginResponse loginResponse) {
            // 4.登录完成之后，更新登录的UI
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {
            // 杀青了
            if (progressDialog != null) {
              progressDialog.dismiss();
            }
          }
        });

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    // 必须这样写，最起码的标准
    if (disposable != null)
      if (!disposable.isDisposed())
        disposable.dispose();
  }
}
