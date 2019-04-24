package com.waelkhelil.immob.model

import android.graphics.Bitmap
import java.util.*
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService



data class Listing(
    val mTitle:String,
    val mType:ListingType,
    val mPhoneNumber:String,
    val mPrice:String,
    val mBitmaps:List<Bitmap>,
    val location: Location
){
    val mPostingDate:Calendar = Calendar.getInstance(Locale.getDefault())
    init {

    }
    enum class ListingType{
        SELL, LOAN
    }
}