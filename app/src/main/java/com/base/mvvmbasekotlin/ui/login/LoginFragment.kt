package com.base.mvvmbasekotlin.ui.login

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.base.mvvmbasekotlin.R
import com.base.mvvmbasekotlin.adapter.LoginTypeAdapter
import com.base.mvvmbasekotlin.base.BaseFragment
import com.base.mvvmbasekotlin.base.adapter.RecyclerViewAdapter
import com.base.mvvmbasekotlin.base.entity.BaseError
import com.base.mvvmbasekotlin.entity.LoginType
import com.base.mvvmbasekotlin.entity.User
import com.base.mvvmbasekotlin.extension.onAvoidDoubleClick
import com.base.mvvmbasekotlin.extension.toast
import com.base.mvvmbasekotlin.extension.visible
import com.base.mvvmbasekotlin.ui.home.HomeFragment
import com.base.mvvmbasekotlin.utils.DeviceUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private val viewModel: LoginViewModel by viewModels()
    private val loginTypeAdapter : LoginTypeAdapter by lazy { LoginTypeAdapter(requireContext()) }

    override fun backFromAddFragment() {
        
    }

    override val layoutId: Int
        get() = R.layout.fragment_login

    override fun initView() {
        loginTypeAdapter.refresh(LoginType.values().toList())
        rcv_social.adapter = loginTypeAdapter
        loginTypeAdapter.addOnItemClickListener(object : RecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(
                adapter: RecyclerView.Adapter<*>,
                viewHolder: RecyclerView.ViewHolder?,
                viewType: Int,
                position: Int
            ) {
                ln_login.visible()
                container.post { container.fullScroll(View.FOCUS_DOWN) }
            }
        })
    }

    override fun initData() {
        
    }

    override fun initListener() {
        btn_login.onAvoidDoubleClick {
            DeviceUtil.hideSoftKeyboard(requireActivity())
            viewModel.login(edt_username.text.toString(),edt_password.text.toString())
        }

        viewModel.loginData.observe(this) {
            handleObjectResponse(it)
        }
    }

    override fun <U> getObjectResponse(data: U) {
        if(data is User){
            getVC().addFragment(HomeFragment::class.java)
        }
    }

    override fun handleValidateError(throwable: BaseError?) {
        throwable?.let {
            toast(it.error)
        }
    }

    override fun backPressed(): Boolean {
        return true
    }
}