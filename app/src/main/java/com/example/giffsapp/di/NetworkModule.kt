package com.example.giffsapp.di

import com.example.giffsapp.data.api.ApiGifService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideApi(): ApiGifService {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor {
                val request = it.request().newBuilder()
                val originalUrl = it.request().url
                val url = originalUrl.newBuilder()
                    .addQueryParameter("api_key", "6Tc0YBYUQtiNgIWxNpXE77rGusPcMtPB")
                    .build()
                request.url(url)
                return@addInterceptor it.proceed(request.build())

            }
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.giphy.com")
            .client(okHttpClient)
            .build()
        return retrofit.create(ApiGifService::class.java)
    }
}