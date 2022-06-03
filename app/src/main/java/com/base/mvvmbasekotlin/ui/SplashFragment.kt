package com.base.mvvmbasekotlin.ui

import android.animation.Animator
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import com.base.mvvmbasekotlin.R
import com.base.mvvmbasekotlin.base.BaseFragment
import com.base.mvvmbasekotlin.ui.home.HomeFragment
import com.base.mvvmbasekotlin.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.splash_fragment.*

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.splash_fragment

    override fun backFromAddFragment() {
    }

    override fun backPressed(): Boolean {
        return false
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initListener() {
        img_anim.setAnimation("splash.json")
        img_anim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                if(!viewModel.isLogin()){
                    getVC().replaceFragment(LoginFragment::class.java)
                }else{
                    getVC().replaceFragment(HomeFragment::class.java)
                }

            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }


    private val viewModel: SplashViewModel by viewModels()
}
