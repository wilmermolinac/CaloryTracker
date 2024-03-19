package com.wamcstudios.calorytracker.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.wamcstudios.calorytracker.core.common.PreferencesConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Singleton
    @Provides
    fun providesSharedPreferences(app: Application):SharedPreferences{
        return app.getSharedPreferences(PreferencesConstant.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
}