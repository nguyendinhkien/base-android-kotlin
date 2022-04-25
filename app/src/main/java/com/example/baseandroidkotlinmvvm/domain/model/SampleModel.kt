package com.example.baseandroidkotlinmvvm.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "SAMPLE")
class SampleModel {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @SerializedName("sample")
    var sample: String? = null
}