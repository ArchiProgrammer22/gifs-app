package com.example.giffsapp.data.local.dao

import androidx.room.*
import com.example.giffsapp.data.local.entities.SimpleGif

@Dao
interface GifDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGifs(simpleGif: List<SimpleGif>)

    @Query("SELECT * FROM simplegif")
    fun getAllGifs(): List<SimpleGif>

    @Delete
    fun deleteGif(gif: SimpleGif)
}