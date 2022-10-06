package com.example.giffsapp.presentation

import com.example.giffsapp.data.local.entities.SimpleGif

interface Repository {
    fun getLocalData(): List<SimpleGif>
    fun insertLocalData(list: List<SimpleGif>)
    fun getRemoteData(): List<SimpleGif>
    fun deleteGif(gif: SimpleGif)
}