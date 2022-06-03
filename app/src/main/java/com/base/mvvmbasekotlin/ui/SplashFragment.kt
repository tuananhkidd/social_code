package com.base.mvvmbasekotlin.ui

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import com.base.mvvmbasekotlin.R
import com.base.mvvmbasekotlin.adapter.SearchAdapter
import com.base.mvvmbasekotlin.base.BaseFragment
import com.base.mvvmbasekotlin.base.permission.PermissionHelper
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.base.mvvmbasekotlin.ui.home.HomeFragment

@AndroidEntryPoint
class SplashFragment : BaseFragment() {
    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(requireContext())
    }
    private val permissionHelper: PermissionHelper by lazy {
        PermissionHelper()
    }

    override val layoutId: Int
        get() = R.layout.splash_fragment

    override fun backFromAddFragment() {
    }

    override fun backPressed(): Boolean {
        return false
    }

    override fun initView() {
        initAdapter()
        viewModel.getData()
        viewModel.data.observe(viewLifecycleOwner, {
        })
    }

    override fun initData() {

    }

    override fun initListener() {
        Looper.getMainLooper()?.let {
            Handler(it).postDelayed({
                getVC().replaceFragment(HomeFragment::class.java)
            },1500)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    private fun initAdapter() {

    }


    private val viewModel: SplashViewModel by viewModels()
}
