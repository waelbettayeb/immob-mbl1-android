package com.waelkhelil.immob

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter(private val list: List<Bitmap>)
    : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val lBitmap: Bitmap = list[position]
        holder.bind(lBitmap)
    }

    override fun getItemCount(): Int = list.size

}