package com.waelkhelil.immob

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class ImageViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_image, parent, false)){
    //TODO : convert this class to a generic class
    private var mBitmap: Bitmap? = null
    private var mImageView : ImageView? = null


    init {
        mImageView = itemView.findViewById(R.id.imageView)
    }

    fun bind(Bitmap: Bitmap) {
        mBitmap = Bitmap
        mImageView?.setImageBitmap(mBitmap)
    }
}
