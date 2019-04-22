package com.waelkhelil.immob.model

import android.graphics.Bitmap
import java.util.*

data class Listing(
    val mTitle:String,
    val mType:ListingType,
    val mPhoneNumber:String,
    val mPrice:String,
    val mBitmaps:List<Bitmap>
){
    val mPostingDate = Calendar.getInstance()

    enum class ListingType{
        SELL, LOAN
    }
}