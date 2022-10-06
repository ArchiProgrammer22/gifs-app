package com.example.giffsapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SimpleGif(
    @ColumnInfo(name = "url")
    @PrimaryKey
    val url: String,

    @ColumnInfo(name = "name")
    val name: String
)