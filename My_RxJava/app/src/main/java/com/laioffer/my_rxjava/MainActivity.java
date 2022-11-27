package com.laioffer.my_rxjava;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.laioffer.my_rxjava.L3_source.demo1_观察者模式.Demo1Activity;
import com.laioffer.my_rxjava.L3_source.demo2_Rx_Hook.Demo2Activity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    startDemoX(R.id.start_demo1_activity, Demo1Activity.class);
    startDemoX(R.id.start_demo2_activity, Demo2Activity.class);
  }

  private void startDemoX(int buttonRes, @NonNull Class<?> clazz) {
    findViewById(buttonRes).setOnClickListener(v -> {
      Intent intent = new Intent(this, clazz);
      startActivity(intent);
    });
  }
}