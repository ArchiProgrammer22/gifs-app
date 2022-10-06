package com.example.giffsapp.data.api

import com.example.giffsapp.domain.enteties.Gif
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiGifService {
    @Headers("api_key: 6Tc0YBYUQtiNgIWxNpXE77rGusPcMtPB")
    @GET("/v1/gifs/trending?&limit=25&offset=0&rating=g&lang=en")
    suspend fun getGifs(): Gif
}