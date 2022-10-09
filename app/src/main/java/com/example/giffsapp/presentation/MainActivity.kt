package com.example.giffsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.giffsapp.R
import com.example.giffsapp.presentation.fragments.GifListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, GifListFragment())
            .commit()
    }
}