package com.example.giffsapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giffsapp.data.local.entities.SimpleGif
import com.example.giffsapp.domain.enteties.Gif
import com.example.giffsapp.presentation.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GifViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val localLiveData: MutableLiveData<List<SimpleGif>> = MutableLiveData()
    private val remoteLiveData: MutableLiveData<List<SimpleGif>> = MutableLiveData()

    fun getLocalData(): List<SimpleGif> {
        initLocalData()
        return localLiveData.value!!
    }

    fun getRemoteData(): List<SimpleGif> {
        initRemoteData()
        return remoteLiveData.value!!
    }

    fun deleteData(gif: SimpleGif) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteGif(gif)
    }

    private fun initLocalData() {
        val deferred = viewModelScope.async(Dispatchers.IO) {
            repository.getLocalData()
        }
        val list: List<SimpleGif>
        runBlocking {
            list = deferred.await()
        }
        localLiveData.value = list
    }

    private fun initRemoteData() {
        val deferred = viewModelScope.async(Dispatchers.IO) {
            repository.getRemoteData()
        }
        val list: Gif
        runBlocking {
            list = deferred.await()
        }
        val simpleList = mapToSimpleGif(list)
        remoteLiveData.value = simpleList

    }

    private fun mapToSimpleGif(list: Gif): List<SimpleGif> {
        val simpleList = mutableListOf<SimpleGif>()
        list.data.forEach {
            val gif = SimpleGif(
                it.images.original.url!!,
                it.title
            )
            simpleList.add(gif)
        }
        return simpleList
    }
}