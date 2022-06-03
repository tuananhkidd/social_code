package com.base.mvvmbasekotlin

import com.base.mvvmbasekotlin.base.BaseActivity
import com.base.mvvmbasekotlin.base.custom.LoadingDialog
import com.base.mvvmbasekotlin.ui.SplashFragment
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {


    override val layoutResId: Int
        get() = R.layout.activity_main
    override val layoutId: Int
        get() = R.id.container

    override fun initListener() {
    }

    override fun initView() {
        FirebaseApp.initializeApp(this)
        getViewController().addFragment(SplashFragment::class.java)
    }

    override fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        LoadingDialog.getInstance(this).destroyLoadingDialog()
    }
}
