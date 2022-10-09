package com.example.giffsapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giffsapp.R
import com.example.giffsapp.data.local.entities.SimpleGif

class GifsPagerAdapter(
    private val list: List<SimpleGif>,
    private val context: Context,
    private val viewPagerCallback: ViewPagerCallback,
) : RecyclerView.Adapter<GifsPagerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.gif_item_pager, parent, false)
        return PagerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        Glide.with(context)
            .load(list[position].url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)

        holder.image.setOnClickListener {
            viewPagerCallback.onClick()
        }
    }

    override fun getItemCount(): Int = list.size

    class PagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.fullScreenImage)
    }
}