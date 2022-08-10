package com.base.mvvmbasekotlin.entity

import androidx.annotation.DrawableRes
import com.base.mvvmbasekotlin.R

enum class LoginType(@DrawableRes var icon : Int) {
    GIAMSAT24(R.drawable.img1),
    RECOVER_DATE(R.drawable.img2),
    ZALO(R.drawable.img3),
    WECHAT(R.drawable.img4),
    VIBER(R.drawable.img5),
    MAP(R.drawable.img6),
    TELEGRAM(R.drawable.img7),
    MESSENGER(R.drawable.img8),
    SMS(R.drawable.img9),
    INSTAGRAM(R.drawable.img10),
    FACEBOOK(R.drawable.img11),
    LINE(R.drawable.img12),
}