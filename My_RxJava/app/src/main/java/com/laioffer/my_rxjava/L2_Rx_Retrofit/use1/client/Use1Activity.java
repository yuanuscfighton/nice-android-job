package com.laioffer.my_rxjava.L2_Rx_Retrofit.use1.client;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding4.view.RxView;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.RxUtils;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use1.api.WangAndroidApi;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use1.bean.ProjectBean;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use1.bean.ProjectItem;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use1.util.HttpUtil;
import com.laioffer.my_rxjava.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;

/**
 * @description 主要是使用场景
 * 1.Retrofit+RxJava查询xxx
 * 2.功能防抖 + 网络嵌套
 * @date 2022/11/20 10:55 AM
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Use1Activity extends AppCompatActivity {

  private final static String TAG = Use1Activity.class.getSimpleName();

  private WangAndroidApi api;
  private Disposable mGetProjectDisposable;
  private Disposable mGetItemDisposable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_use);

    // 初始化
    api = HttpUtil.getOnlineCookieRetrofit().create(WangAndroidApi.class);

    // 按钮1: 获取项目的整个分类的数据
    findViewById(R.id.l2_use1_button1).setOnClickListener(v -> {
      getAllCategoriesData1();
      // getAllCategoriesData2();
    });

    // 按钮2: 获取某一个分类下项目列表数据
    findViewById(R.id.l2_use1_button2).setOnClickListener(v -> {
      getOneCategoryData();
    });

    // 功能防抖 + 网络嵌套
    // antiShakeActon();
    antiShakeActonUpdate();
  }

  /**
   * Retrofit+RxJava 获取项目的整个分类的数据
   * 使用Consumer
   */
  public void getAllCategoriesData1() {
    // 获取网络API
    mGetProjectDisposable = api.getProject()
        .subscribeOn(Schedulers.io()) // 给上面分配异步线程
        .observeOn(AndroidSchedulers.mainThread()) // 给下面分配主线程
        .subscribe(new Consumer<ProjectBean>() { // Consumer简化版本的Observer
          @Override
          public void accept(ProjectBean projectBean) {
            Log.d(TAG, "accept: " + projectBean); // UI 可以做事情
          }
        });
  }

  /**
   * Retrofit+RxJava 获取项目的整个分类的数据
   * 使用Observer
   */
  public void getAllCategoriesData2() {
    // 获取网络API
    api.getProject()
        .subscribeOn(Schedulers.io()) // 给上面分配异步线程
        .observeOn(AndroidSchedulers.mainThread()) // 给下面分配主线程
        .subscribe(new Observer<ProjectBean>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {

          }

          @Override
          public void onNext(@NonNull ProjectBean projectBean) {

          }

          @Override
          public void onError(@NonNull Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });
  }

  /**
   * Retrofit+RxJava 获取某一个分类下项目列表的数据
   */
  public void getOneCategoryData() {
    // 注意:这里的 294 是项目分类 所查询出来的数据
    // 上面的项目分类会查询出："id": 294,"id": 402,"id": 367,"id": 323,"id": 314, ...

    // id 写死的
    mGetItemDisposable = api.getProjectItem(1, 294)
        // .....
        .subscribeOn(Schedulers.io()) // 上面 异步
        .observeOn(AndroidSchedulers.mainThread()) // 下面 主线程
        .subscribe(data -> {
          Log.d(TAG, "getProjectListAction: " + data);
        });

  }

  /**
   * RxBinding 防抖
   * <p>
   * 功能防抖 + 网络嵌套 ==> 这种是负面教程，嵌套的太厉害了
   */
  private void antiShakeActon() {
    // 注意：（项目分类）查询的id，通过此id再去查询(项目列表数据)

    // 对那个控件防抖动？
    Button bt_anti_shake = findViewById(R.id.bt_anti_shake);

    RxView.clicks(bt_anti_shake) // 起点是把Button传进来，Button就是事件
        .throttleFirst(2000, TimeUnit.MILLISECONDS) // 2秒钟之内，只响应一次
        .subscribe(new Consumer<Unit>() {
          @Override
          public void accept(Unit ignore) {
            api.getProject() // 查询主数据
                .compose(RxUtils.rxud())
                .subscribe(new Consumer<ProjectBean>() {
                  @Override
                  public void accept(ProjectBean projectBean) {
                    for (ProjectBean.DataBean dataBean : projectBean.getData()) { // 10
                      // 查询item数据
                      api.getProjectItem(1, dataBean.getId())
                          .compose(RxUtils.rxud())
                          .subscribe(new Consumer<ProjectItem>() {
                            @Override
                            public void accept(ProjectItem projectItem) {
                              Log.d(TAG, "accept: " + projectItem); // 可以UI操作
                            }
                          });
                    }
                  }
                });
          }
        });
  }


  /**
   * 功能防抖 + 网络嵌套 <== 使用flatMap，解决嵌套的问题
   */
  @SuppressLint("CheckResult")
  private void antiShakeActonUpdate() {
    // 注意：项目分类查询的id，通过此id再去查询(项目列表数据)

    // 对那个控件防抖动？
    Button bt_anti_shake = findViewById(R.id.bt_anti_shake);

    // 防抖是在主线程的
    RxView.clicks(bt_anti_shake)
        .throttleFirst(2000, TimeUnit.MILLISECONDS) // 2秒钟之内 响应你一次

        // 只给下面 切换 异步
        .observeOn(Schedulers.io())
        .flatMap(new Function<Unit, ObservableSource<ProjectBean>>() {
          @Override
          public ObservableSource<ProjectBean> apply(Unit ignore) {
            return api.getProject(); // 主数据
          }
        })
        .flatMap(new Function<ProjectBean, ObservableSource<ProjectBean.DataBean>>() {
          @Override
          public ObservableSource<ProjectBean.DataBean> apply(ProjectBean projectBean) {
            return Observable.fromIterable(projectBean.getData()); // 自己创建一个发射器，发多次
            // 和上面的for(ProjectBean.DataBean dataBean : projectBean.getData()) 等价
          }
        })
        .flatMap(new Function<ProjectBean.DataBean, ObservableSource<ProjectItem>>() {
          @Override
          public ObservableSource<ProjectItem> apply(ProjectBean.DataBean dataBean) {
            return api.getProjectItem(1, dataBean.getId());
          }
        })

        .observeOn(AndroidSchedulers.mainThread()) // 给下面切换 主线程
        .subscribe(new Consumer<ProjectItem>() {
          @Override
          public void accept(ProjectItem projectItem) {
            // 如果我要更新UI 会报错2 不会报错1
            Log.d(TAG, "accept: " + projectItem);
          }
        });
  }

}
