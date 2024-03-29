package com.waelkhelil.immob

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.waelkhelil.immob.model.Listing


class MainViewModel : ViewModel() {

    val mBitmaps: MutableLiveData<List<Bitmap>> = MutableLiveData(listOf())
    private val mNewBitmaps = mutableListOf<Bitmap>()

    val mListing: MutableLiveData<List<Listing>> = MutableLiveData(listOf())
    private val newListing = mutableListOf<Listing>()

    fun addBitmaps(vararg bitmap: Bitmap){
        mNewBitmaps.addAll(bitmap)
        mBitmaps.postValue(mNewBitmaps)
    }
    fun freeBitmaps(){
        mNewBitmaps.clear()
        mBitmaps.postValue(mNewBitmaps)
    }
    fun addListing(vararg listing: Listing){
        newListing.addAll(listing)
        mListing.postValue(newListing)
        freeBitmaps()
    }
}