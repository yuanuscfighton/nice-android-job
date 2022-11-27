package com.laioffer.my_rxjava.L2_Rx_Retrofit.use1.api;


import com.laioffer.my_rxjava.L2_Rx_Retrofit.use1.bean.ProjectBean;
import com.laioffer.my_rxjava.L2_Rx_Retrofit.use1.bean.ProjectItem;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @description 客户端api
 * @date 2022/11/19 9:32 PM
 * <p>
 * 服务器api接口:
 * (1) 项目为包含一个分类，该接口返回整个分类: https://www.wanandroid.com/project/tree/json
 * (2) 某一个分类下项目列表数据:             https://www.wanandroid.com/project/list/1/json?cid=294
 * <p>
 * https://www.wanandroid.com 这部分是服务器的域名，作为BASE_URL
 */
public interface WangAndroidApi {

  /**
   * 获取总数据
   *
   * @return 1.接口的返回值类型是Observable，作为起点
   * 2.ProjectBean就是事件
   */
  @GET("project/tree/json")
  Observable<ProjectBean> getProject();  // 异步线程 耗时操作

  // Item数据
  @GET("project/list/{pageIndex}/json")
  // ?cid=294
  Observable<ProjectItem> getProjectItem(
      @Path("pageIndex") int pageIndex,
      @Query("cid") int cid);  // 异步线程 耗时操作
}
