package com.base.mvvmbasekotlin.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.base.mvvmbasekotlin.base.BaseViewModel
import com.base.mvvmbasekotlin.base.entity.BaseError
import com.base.mvvmbasekotlin.base.entity.BaseListLoadMoreResponse
import com.base.mvvmbasekotlin.entity.User
import com.base.mvvmbasekotlin.extension.ListLoadMoreResponse
import com.base.mvvmbasekotlin.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(var repo: Repository) : BaseViewModel() {
    var data: ListLoadMoreResponse<User> = MutableLiveData()
    var pageIndex = 1
    fun getData(isRefresh: Boolean = true) {
        data.value = BaseListLoadMoreResponse<User>().error(throwable = BaseError("ahhuhu",11),isShowingError = false)

    }

    fun refreshData() {
        pageIndex = 1
        getData()
    }

    fun login() {
        mDisposable.add(
            repo.login("tuananhkidd@gmail.com","123456")
                .doOnSubscribe {

                }
                .subscribe(
                    {
                        Log.v("ahuhu","data ${it.data?.accessToken}")
                    },
                    {
                        Log.v("ahuhu","error ${it.message}")
                    }
                )
        )
    }
}
