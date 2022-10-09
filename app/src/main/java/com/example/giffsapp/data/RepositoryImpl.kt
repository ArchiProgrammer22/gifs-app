package com.example.giffsapp.data

import com.example.giffsapp.data.api.ApiGifService
import com.example.giffsapp.data.local.dao.GifDao
import com.example.giffsapp.data.local.entities.SimpleGif
import com.example.giffsapp.domain.enteties.Gif
import com.example.giffsapp.presentation.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: GifDao,
    private val apiGifService: ApiGifService
) : Repository {

    override suspend fun getLocalData(): List<SimpleGif> {
        return dao.getAllGifs()
    }

    override suspend fun insertLocalData(list: List<SimpleGif>) {
        dao.insertGifs(list)
    }

    override suspend fun getRemoteData(): Gif {
        return apiGifService.getGifs()
    }

    override suspend fun deleteGif(gif: SimpleGif) {
        dao.deleteGif(gif)
    }
}