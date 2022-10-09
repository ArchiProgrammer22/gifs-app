package com.example.giffsapp.di

import com.example.giffsapp.presentation.fragments.GifFullscreenFragment
import com.example.giffsapp.presentation.fragments.GifListFragment
import dagger.Component

@Component(modules = [DatabaseModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(gifListFragment: GifListFragment)
    fun inject(gifFullscreenFragment: GifFullscreenFragment)
}