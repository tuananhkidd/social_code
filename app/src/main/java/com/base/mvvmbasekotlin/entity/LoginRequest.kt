package com.base.mvvmbasekotlin.entity

import com.google.gson.annotations.SerializedName

class LoginRequest {
    @SerializedName("username")
    var username:String? = null
    @SerializedName("password")
    var password:String? = null
}