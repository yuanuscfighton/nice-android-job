package com.laioffer.my_glide.glide;

import android.content.Context;
import android.util.Log;

import com.laioffer.my_glide.glide.binding.inter.Lifecycle;
import com.laioffer.my_glide.glide.binding.inter.LifecycleListener;
import com.laioffer.my_glide.glide.util.LOG;

public class RequestManager implements LifecycleListener {

  private Lifecycle lifecycle;

  public RequestManager(Glide glide, Lifecycle lifecycle, Context applicationContext) {
    this.lifecycle = lifecycle;

    this.lifecycle.addListener(this); // 构造函数 已经给自己注册了【自己给自己绑定】
  }

  // Activity/Fragment 可见时恢复请求 （onStart() ） 掉用函数
  @Override
  public void onStart() {
    Log.d(LOG.TAG, "开始执行生命周期业务 onStart: 运行队列 全部执行，等待队列 全部清空 ....");
  }

  // Activity/Fragment 不可见时暂停请求 （onStop() ） 掉用函数
  @Override
  public void onStop() {
    Log.d(LOG.TAG, "开始执行生命周期业务 onStop: 运行队列 全部停止，把任务都加入到等待队列 ....");
  }

  @Override
  public void onDestroy() {
    Log.d(LOG.TAG, "开始执行生命周期业务 onDestroy: 自己负责移除自己绑定的生命周期监听，释放操作 ....");
    this.lifecycle.removeListener(this); // 已经给自己销毁了 【自己给自己移除】
  }
}
