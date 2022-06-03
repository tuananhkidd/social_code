package com.base.mvvmbasekotlin.share_preference

import android.content.Context
import android.content.SharedPreferences
import com.base.mvvmbasekotlin.extension.clearAll
import com.base.mvvmbasekotlin.extension.setBoolean
import com.google.gson.Gson
import java.lang.Exception
import javax.inject.Inject

class AppSharePrefImpl @Inject constructor(var context: Context) : AppSharePref {

    companion object {
        const val KEY_PREF = "KEY_PREF"
        const val KEY_PREF_USER = "KEY_PREF_USER"
        const val KEY_PREF_IS_LOGIN = "KEY_PREF_IS_LOGIN"
    }

    private var mPrefs: SharedPreferences? = null

    init {
        mPrefs = context.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE)
    }


    override var isLogin: Boolean
        get() = mPrefs?.getBoolean(KEY_PREF_IS_LOGIN,false) ?: false
        set(value) {
            mPrefs?.setBoolean(KEY_PREF_IS_LOGIN,value)
        }

    override fun logout() {
        mPrefs?.clearAll()
    }
}