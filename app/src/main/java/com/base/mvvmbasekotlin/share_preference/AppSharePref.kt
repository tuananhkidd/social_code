package com.base.mvvmbasekotlin.share_preference

import javax.inject.Singleton

@Singleton
interface AppSharePref {
    var isLogin : Boolean

    fun logout()

}