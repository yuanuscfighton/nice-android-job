package com.laioffer.my_glide.source_code

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.laioffer.my_glide.R

/**
 * @description Glide源码
 *              画图: https://www.processon.com/diagraming/63759740e0b34d37c44f3c84
 * @date        2022/11/17 9:56 AM
 */
class Demo1Activity : AppCompatActivity() {

  private val IMAGE_URL =
    "http://img2_bbs.redocn.com/attachments/2017/20170803/20170803_7680f9d16922b32c6291qh0u6XK9J40T.jpg";

  private lateinit var myImageView: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    myImageView = findViewById(R.id.my_glide_view)

    Glide.with(this).load(IMAGE_URL).into(myImageView)
  }

  /**
   * Glide内部会有一个监听onDestroy自动回收机制
   */
  override fun onDestroy() {
    super.onDestroy()
    // 自动会clear掉，不需要手动clear了
    Glide.with(this).clear(myImageView)
  }
}