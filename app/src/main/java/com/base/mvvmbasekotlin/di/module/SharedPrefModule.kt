package com.base.mvvmbasekotlin.di.module

import com.base.mvvmbasekotlin.share_preference.AppSharePref
import com.base.mvvmbasekotlin.share_preference.AppSharePrefImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefModule {
    @Provides
    @Singleton
    fun provideSharePref(sharedPref: AppSharePrefImpl): AppSharePref {
        return sharedPref
    }
}