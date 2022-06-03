package com.base.mvvmbasekotlin.network


import com.base.mvvmbasekotlin.base.entity.BaseListLoadMoreResponse
import com.base.mvvmbasekotlin.base.entity.BaseObjectResponse
import com.base.mvvmbasekotlin.entity.LoginRequest
import com.base.mvvmbasekotlin.entity.LoginResponse
import com.base.mvvmbasekotlin.entity.User
import io.reactivex.Single
import retrofit2.http.*

interface ApiInterface {

    @GET("search1")
    @Headers("Content-Type: application/json", "lang: vi")
    fun getDataUser(
        @Query("s") keyWord: String,
        @Query("page") page: Int
    ): Single<BaseListLoadMoreResponse<User>>

    @POST("/api/auth/login/customer")
    @Headers("Content-Type: application/json")
    fun login(@Body loginRequest:LoginRequest) : Single<BaseObjectResponse<LoginResponse>>
}
