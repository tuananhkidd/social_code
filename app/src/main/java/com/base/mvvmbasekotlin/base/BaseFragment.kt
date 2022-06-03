package com.base.mvvmbasekotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.base.mvvmbasekotlin.base.custom.LoadingDialog
import com.base.mvvmbasekotlin.base.entity.BaseError
import com.base.mvvmbasekotlin.base.entity.BaseListLoadMoreResponse
import com.base.mvvmbasekotlin.base.entity.BaseListResponse
import com.base.mvvmbasekotlin.base.entity.BaseObjectResponse
import com.base.mvvmbasekotlin.utils.Define

abstract class BaseFragment : Fragment() {

    private var viewController : ViewController? = null

//    private val loadingDialog : LoadingDialog by lazy { LoadingDialog(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initListener()
    }

    abstract fun backFromAddFragment()

    @get: LayoutRes
    protected abstract val layoutId :  Int
    protected abstract fun initView()
    protected abstract fun initData()
    protected abstract fun initListener()
    abstract fun backPressed() : Boolean

    fun setVC(viewController: ViewController) {
        this.viewController = viewController
    }

    fun getVC() : ViewController {
        if(viewController == null){
            viewController =  (activity as BaseActivity).getViewController()
        }

        return viewController!!
    }

    protected open fun handleListResponse(response: BaseListResponse<*>) {
        when (response.type) {
            Define.ResponseStatus.LOADING -> showLoading()
            Define.ResponseStatus.SUCCESS -> {
                hideLoading()
                getListResponse(response.data)
            }
            Define.ResponseStatus.ERROR -> {
                hideLoading()
                if (response.isShowingError) {
                    handleNetworkError(response.error, true)
                } else {
                    handleValidateError(response.error as? BaseError?)
                }
            }
        }
    }

    protected open fun <U> handleObjectResponse(response: BaseObjectResponse<U>) {
        when (response.type) {
            Define.ResponseStatus.LOADING -> showLoading()
            Define.ResponseStatus.SUCCESS -> {
                hideLoading()
                getObjectResponse(response.data)
            }
            Define.ResponseStatus.ERROR -> {
                hideLoading()
                if (response.isShowingError) {
                    handleNetworkError(response.error, true)
                } else {
                    handleValidateError(response.error as? BaseError?)
                }
            }
        }
    }

    open fun <U> getObjectResponse(data : U){

    }

    open fun <U> getListResponse(data: List<U>?){

    }

    protected open fun handleValidateError(throwable: BaseError?) {}


    protected open fun handleNetworkError(throwable: Throwable?, isShowDialog: Boolean) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity?)?.handleNetworkError(
                throwable,
                isShowDialog
            )
        }
    }

    protected fun showLoading(){
        LoadingDialog.getInstance(requireContext()).show()
    }

    protected fun hideLoading(){
        LoadingDialog.getInstance(requireContext()).hidden()
    }

}