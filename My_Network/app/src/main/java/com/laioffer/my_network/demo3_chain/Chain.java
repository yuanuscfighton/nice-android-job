package com.laioffer.my_network.demo3_chain;

import java.util.List;

/**
 * @description 组成链条
 * @date 2022/11/15 7:51 AM
 */
public class Chain {
  // 拦截器的集合
  private final List<Interceptor> mInterceptors;
  // 从第几个拦截器去处理
  private final int mIndex;
  // 请求
  public String mRequest;

  public Chain(List<Interceptor> interceptors, int index, String request) {
    this.mInterceptors = interceptors;
    this.mIndex = index;
    this.mRequest = request;
  }

  public Chain(List<Interceptor> interceptors, int index) {
    this.mInterceptors = interceptors;
    this.mIndex = index;
  }

  /**
   * 处理的方法，把请求交给拦截器，逐个去处理
   *
   * @param request 请求
   * @return
   */
  public String proceed(String request) {
    if (mIndex >= mInterceptors.size()) {
      throw new AssertionError();
    }

    // 创建一个新的链条，其中index 需要加1
    Chain chain = new Chain(mInterceptors, mIndex + 1, request);
    // 从链条中取出一个拦截器
    Interceptor interceptor = mInterceptors.get(mIndex);
    // 执行请求
    return interceptor.intercept(chain);

    // e.g.
    // index=0的拦截器是 RetryAndFollowUpInterceptor，
    // 在 RetryAndFollowUpInterceptor#intercept()拿到的链条是我们新创建的index=1的链条
  }
}