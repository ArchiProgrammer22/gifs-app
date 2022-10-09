package com.example.giffsapp.presentation

import com.example.giffsapp.data.local.entities.SimpleGif
import com.example.giffsapp.domain.enteties.Gif

interface Repository {
    suspend fun getLocalData(): List<SimpleGif>
    suspend fun insertLocalData(list: List<SimpleGif>)
    suspend fun getRemoteData(): Gif
    suspend fun deleteGif(gif: SimpleGif)
}