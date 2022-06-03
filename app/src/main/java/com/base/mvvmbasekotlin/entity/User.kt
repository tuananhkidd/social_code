package com.base.mvvmbasekotlin.entity

import com.google.gson.annotations.SerializedName

 class User{
     constructor()

     @SerializedName("user_name")
     var name: String =""
     @SerializedName("code")
     var code: String = ""
     @SerializedName("status")
     var status : Int = 1
 }