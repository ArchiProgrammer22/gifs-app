package com.example.giffsapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.giffsapp.GifApplication
import com.example.giffsapp.R
import com.example.giffsapp.data.local.entities.SimpleGif
import com.example.giffsapp.presentation.adapters.GifsPagerAdapter
import com.example.giffsapp.presentation.adapters.ViewPagerCallback
import com.example.giffsapp.presentation.viewmodels.GifViewModel
import javax.inject.Inject

class GifFullscreenFragment : Fragment(), ViewPagerCallback {

    @Inject
    lateinit var viewModel: GifViewModel

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireContext().applicationContext as GifApplication)
            .appComponent
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gif_fullscreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = checkType(arguments?.getString("type")!!)

        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = GifsPagerAdapter(item, requireContext(), this)
        viewPager.currentItem = arguments?.getInt("id")!!
    }

    override fun onClick() {
        requireActivity().supportFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

    private fun checkType(type: String): List<SimpleGif> {
        return if (type == getString(R.string.remote)) viewModel.getRemoteData()
        else viewModel.getLocalData()

    }
}