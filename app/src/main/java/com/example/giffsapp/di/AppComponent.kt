package com.example.giffsapp.di

import com.example.giffsapp.presentation.fragments.GifFullscreenFragment
import com.example.giffsapp.presentation.fragments.GifRemoteListFragment
import dagger.Component

@Component(modules = [DatabaseModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(gifRemoteListFragment: GifRemoteListFragment)
    fun inject(gifFullscreenFragment: GifFullscreenFragment)
}