package com.example.giffsapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.giffsapp.di.DaggerAppComponent
import com.example.giffsapp.di.DatabaseModule
import com.example.giffsapp.di.NetworkModule

class GifApplication : Application() {

    lateinit var appComponent: DaggerAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(applicationContext))
            .build() as DaggerAppComponent
    }
}