package com.waelkhelil.immob

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData



class MainViewModel : ViewModel() {
    private val bitmaps: MutableLiveData<List<Bitmap>>? = MutableLiveData()

}