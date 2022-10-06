package com.example.giffsapp.data


import com.example.giffsapp.data.api.ApiGifService
import com.example.giffsapp.data.local.dao.GifDao
import com.example.giffsapp.data.local.entities.SimpleGif
import com.example.giffsapp.domain.enteties.Gif
import com.example.giffsapp.presentation.Repository
import kotlinx.coroutines.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val dao: GifDao,
    val apiGifService: ApiGifService
) : Repository {
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun getLocalData(): List<SimpleGif> {
        val deferred = scope.async {
            dao.getAllGifs()
        }
        val list: List<SimpleGif>
        runBlocking {
            list = deferred.await()
        }
        return list
    }

    override fun insertLocalData(list: List<SimpleGif>) {
        scope.launch {
            dao.insertGifs(list)
        }
    }

    override fun getRemoteData(): List<SimpleGif> {
        val deferred = scope.async {
            apiGifService.getGifs()
        }
        val list: Gif
        runBlocking {
            list = deferred.await()
        }
        val simpleList = mutableListOf<SimpleGif>()
        list.data.forEach {
            val gif = SimpleGif(
                it.images.original.url!!,
                it.title
            )
            simpleList.add(gif)
        }
        insertLocalGifs(simpleList)
        return simpleList
    }

    override fun deleteGif(gif: SimpleGif) {
        scope.launch {
            dao.deleteGif(gif)
        }
    }

    private fun insertLocalGifs(gifs: List<SimpleGif>) {
        scope.launch {
            dao.insertGifs(gifs)
        }
    }
}