package com.example.giffsapp.di

import android.content.Context
import androidx.room.Room
import com.example.giffsapp.data.RepositoryImpl
import com.example.giffsapp.data.api.ApiGifService
import com.example.giffsapp.data.local.GifDatabase
import com.example.giffsapp.data.local.dao.GifDao
import com.example.giffsapp.presentation.Repository
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(
    private val context: Context
) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideRepository(dao: GifDao, api: ApiGifService): Repository {
        return RepositoryImpl(dao, api)
    }

    @Provides
    fun provideDbDao(context: Context): GifDao {
        val db = Room.databaseBuilder(
            context,
            GifDatabase::class.java,
            "gifDb"
        ).build()
        return db.getGifDao()
    }
}