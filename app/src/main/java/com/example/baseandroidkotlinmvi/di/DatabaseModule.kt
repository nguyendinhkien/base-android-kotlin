package com.example.baseandroidkotlinmvi.di

import android.app.Application
import androidx.room.Room
import com.example.baseandroidkotlinmvi.data.source.local.AppDatabase
import com.example.baseandroidkotlinmvi.data.source.local.dao.SampleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }


    @Provides
    internal fun provideSampleDao(appDatabase: AppDatabase): SampleDao {
        return appDatabase.sampleDao()
    }
}