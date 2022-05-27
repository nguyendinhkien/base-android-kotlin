package com.example.baseandroidkotlinmvi.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.baseandroidkotlinmvi.data.source.preferences.PrefsHelperImpl
import com.example.baseandroidkotlinmvi.domain.preferences.PrefsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SharedPrefsModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun providePrefsHelper(sharedPreferences: SharedPreferences): PrefsHelper {
        return PrefsHelperImpl(sharedPreferences)
    }
}