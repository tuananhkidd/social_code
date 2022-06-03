package com.base.mvvmbasekotlin.ui.login

import android.text.TextUtils
import com.base.mvvmbasekotlin.base.BaseViewModel
import com.base.mvvmbasekotlin.base.entity.BaseError
import com.base.mvvmbasekotlin.base.entity.BaseObjectResponse
import com.base.mvvmbasekotlin.entity.User
import com.base.mvvmbasekotlin.extension.ObjectResponse
import com.base.mvvmbasekotlin.share_preference.AppSharePref
import com.base.mvvmbasekotlin.utils.DISABLE
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val appSharePref: AppSharePref) : BaseViewModel() {

    var loginData = ObjectResponse<User>()

    fun login(userName :  String?, code : String?){

        if(TextUtils.isEmpty(userName)){
            loginData.value = BaseObjectResponse<User>().error(BaseError("Nhập tài khoản",1),false)
            return
        }
        if(TextUtils.isEmpty(code)){
            loginData.value = BaseObjectResponse<User>().error(BaseError("Nhập code",1),false)
            return
        }
        val db =  FirebaseFirestore.getInstance()
        val accountCollection = db.collection("account")
        loginData.value = BaseObjectResponse<User>().loading()

        accountCollection.whereEqualTo(
            "user_name",userName
        )
            .whereEqualTo(
                "code",code
            ).get()
            .addOnSuccessListener {
                if(it.documents.isEmpty()){
                    loginData.value = BaseObjectResponse<User>().error(BaseError("Tài khoản hoặc code không chính xác",1),false)
                }else{
                    val doc = it.documents.first()
                    val user = doc.toObject(User::class.java)
                    user?.status = DISABLE

                    db.collection("account")
                        .document(doc.id)
                        .update("status", DISABLE)
                        .addOnSuccessListener {
                            appSharePref.isLogin = true
                            loginData.value = BaseObjectResponse<User>().success(user!!)
                        }.addOnFailureListener {
                            loginData.value = BaseObjectResponse<User>().error(it)
                        }
                }
            }
            .addOnFailureListener {
                loginData.value = BaseObjectResponse<User>().error(it)
            }

    }
}