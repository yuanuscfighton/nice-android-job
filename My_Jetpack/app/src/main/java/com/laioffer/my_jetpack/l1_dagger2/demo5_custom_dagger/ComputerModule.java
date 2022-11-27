package com.laioffer.my_jetpack.l1_dagger2.demo5_custom_dagger;

import com.laioffer.custom_dagger.annation.Module;
import com.laioffer.custom_dagger.annation.Provides;

/**
 * @description 第2个注解: @Module
 * @date 2022/10/6 11:38 上午
 */
@Module
public class ComputerModule {

  /**
   * 第3个注解: Provider
   */
  @Provides
  public Computer providerStudent() {
    return new Computer();
  }
}
