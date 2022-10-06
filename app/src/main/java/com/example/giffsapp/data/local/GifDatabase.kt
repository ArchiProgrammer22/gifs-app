package com.example.giffsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.giffsapp.data.local.dao.GifDao
import com.example.giffsapp.data.local.entities.SimpleGif

@Database(entities = [SimpleGif::class], version = 1)
abstract class GifDatabase : RoomDatabase() {
    abstract fun getGifDao(): GifDao
}