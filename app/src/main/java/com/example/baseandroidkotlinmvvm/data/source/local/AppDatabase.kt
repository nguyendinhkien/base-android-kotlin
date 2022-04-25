package com.example.baseandroidkotlinmvvm.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseandroidkotlinmvvm.data.source.local.dao.SampleDao

@Database(version = 1)
abstract class AppDatabase  : RoomDatabase(){

    abstract fun sampleDao(): SampleDao

    companion object {
        const val DB_NAME = "MyDatabase.db"
    }
}