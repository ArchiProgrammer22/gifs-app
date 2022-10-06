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

class GifsAdapter(
    private val list: List<SimpleGif>,
    private val context: Context,
    private val onGifCallback: GifCallback,
) : RecyclerView.Adapter<GifsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.gif_item_recycler, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(list[position].url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.image)
        holder.image.setOnClickListener {
            onGifCallback.onClick(list, position)
        }
        holder.image.setOnLongClickListener {
            onGifCallback.onLongClick(it, list, position)
        }
    }

    override fun getItemCount() = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.recyclerImage)
    }
}