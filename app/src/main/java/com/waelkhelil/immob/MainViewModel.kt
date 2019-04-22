package com.waelkhelil.immob

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData



class MainViewModel : ViewModel() {
    val bitmaps: MutableLiveData<MutableList<Bitmap>> = MutableLiveData()
    private val newBitmaps = mutableListOf<Bitmap>()
    val listing: MutableLiveData<MutableList<Bitmap>> = MutableLiveData()
    private val newListing = mutableListOf<Bitmap>()

    fun addBitmaps(vararg bitmap: Bitmap){
        newBitmaps.addAll(bitmap)
        bitmaps.postValue(newBitmaps)
    }
}