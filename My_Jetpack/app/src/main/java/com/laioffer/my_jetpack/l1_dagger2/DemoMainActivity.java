package com.laioffer.my_jetpack.l1_dagger2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.dagger2.R;

/**
 * @description
 * @date 2022/9/18 11:19 下午
 */
public class DemoMainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo_main);
  }
}
