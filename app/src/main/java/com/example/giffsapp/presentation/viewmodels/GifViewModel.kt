package com.example.giffsapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giffsapp.data.local.entities.SimpleGif
import com.example.giffsapp.presentation.Repository
import javax.inject.Inject

class GifViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    var localLiveData: MutableLiveData<List<SimpleGif>> = MutableLiveData()
        private set

    var remoteLiveData: MutableLiveData<List<SimpleGif>> = MutableLiveData()
        private set

    init {
        localLiveData.value = repository.getLocalData()
        remoteLiveData.value = repository.getRemoteData()
    }


    fun getLocalData(): List<SimpleGif> {
        localLiveData.value = repository.getLocalData()
        return localLiveData.value!!
    }

    fun getRemoteData(): List<SimpleGif> {
        remoteLiveData.value = repository.getRemoteData()
        return remoteLiveData.value!!
    }

    fun deleteData(gif: SimpleGif) = repository.deleteGif(gif)
}