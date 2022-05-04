package com.example.baseandroidkotlinmvvm.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "SAMPLE")
class SampleModel {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("hex")
    var hex: String? = null
}