package com.base.mvvmbasekotlin.ui

import com.base.mvvmbasekotlin.base.BaseViewModel
import com.base.mvvmbasekotlin.share_preference.AppSharePref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val appSharePref: AppSharePref) : BaseViewModel() {

    fun isLogin() : Boolean = appSharePref.isLogin
}
