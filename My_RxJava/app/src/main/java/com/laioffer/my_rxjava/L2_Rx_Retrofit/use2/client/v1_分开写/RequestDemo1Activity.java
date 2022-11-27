package com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.client.v1_分开写;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.my_rxjava.L2_Rx_Retrofit.RxUtils;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.MyRetrofit;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.api.IRequestNetwork;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.api.LoginRequest;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.api.RegisterRequest;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.bean.LoginResponse;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use2.bean.RegisterResponse;
import com.laioffer.my_rxjava.R;

import io.reactivex.rxjava3.functions.Consumer;


/**
 * @description 需求: 频繁的线程切换
 * @date 2022/11/20 2:56 PM
 * 具体内容:
 * (1)请求服务器注册操作 --> 耗时操作
 * (2)注册完成之后，更新注册UI
 * (3)马上去登录服务器操作 --> 耗时操作
 * (4)登录完成之后，更新登录的UI
 * <p>
 * RxJava配合Retrofit (请求网络OkHttp -- Retrofit -- Observable)
 * <p>
 * 1.OkHttp 请求网络(Retrofit)
 * 2.Retrofit 返回一个结果 （Retrofit） --- Observable
 * 3.最终的结果 是RxJava中的 被观察者 上游 Observable
 * 4.一行代码写完需求流程： 从上往下
 * 1.请求服务器，执行注册操作（耗时）切换异步线程
 * 2.更新注册后的所有 注册相关UI - main 切换主线程
 * 3.请求服务器，执行登录操作（耗时）切换异步线程
 * 4.更新登录后的所有 登录相关UI - main 切换主线程
 * <p>
 * 5.看RxJava另外一种的执行流程
 * 初始点 开始点 订阅
 * 1.onSubscribe
 * 2.registerAction(new RegisterRequest())
 * 3..doOnNext 更新注册后的 所有UI
 * 4.flatMap执行登录的耗时操作
 * 5.订阅的观察者 下游 onNext 方法，更新所有登录后的UI
 * 6.progressDialog.dismiss()
 */
public class RequestDemo1Activity extends AppCompatActivity {

  private final String TAG = RequestDemo1Activity.class.getSimpleName();

  private TextView tv_register_ui;
  private TextView tv_login_ui;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_request);

    tv_register_ui = findViewById(R.id.tv_login_ui);
    tv_login_ui = findViewById(R.id.tv_login_ui);

  }

  // 方式1: 分开写
  @SuppressWarnings("ResultOfMethodCallIgnored")
  public void request(View view) {
    // 1.请求服务器注册操作
    MyRetrofit.createRetrofit().create(IRequestNetwork.class)
        // 耗时操作，分配子线程
        .registerAction(new RegisterRequest())
        .compose(RxUtils.rxud())
        .subscribe(new Consumer<RegisterResponse>() {
          @Override
          public void accept(RegisterResponse registerResponse) throws Exception {
            // 2.注册完成之后，更新注册UI
            // .....
          }
        });

    // 3.马上去登录服务器操作
    MyRetrofit.createRetrofit().create(IRequestNetwork.class)
        .loginAction(new LoginRequest())
        .compose(RxUtils.rxud())
        .subscribe(new Consumer<LoginResponse>() {
          @Override
          public void accept(LoginResponse loginResponse) throws Exception {
            // 4.登录完成之后，更新登录的UI
            // .....
          }
        });
  }

}
