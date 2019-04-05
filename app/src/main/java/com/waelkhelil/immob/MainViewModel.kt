package com.waelkhelil.immob

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData



class MainViewModel : ViewModel() {
    val bitmaps: MutableLiveData<MutableList<Bitmap>> = MutableLiveData()
    var onChange: ()->Unit = {}

    fun addBitmaps(vararg bitmap: Bitmap){
        bitmaps.value?.addAll(bitmap)
        bitmaps.postValue(bitmaps.value)
    }
}