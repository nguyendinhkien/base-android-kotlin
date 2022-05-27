package com.example.baseandroidkotlinmvi.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseandroidkotlinmvi.data.source.local.dao.SampleDao
import com.example.baseandroidkotlinmvi.domain.model.SampleModel

@Database(entities = [SampleModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sampleDao(): SampleDao

    companion object {
        const val DB_NAME = "MyDatabase.db"
    }
}