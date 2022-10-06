package com.example.giffsapp.presentation.adapters

import android.view.View
import com.example.giffsapp.data.local.entities.SimpleGif


interface GifCallback {
    fun onClick(list: List<SimpleGif>, id: Int)
    fun onLongClick(view: View, list: List<SimpleGif>, id: Int): Boolean
}