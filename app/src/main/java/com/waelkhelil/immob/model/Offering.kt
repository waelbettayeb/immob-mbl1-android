package com.waelkhelil.immob.model

import java.util.*

data class Offering(
    val mTitle:String,
    val mPhoneNumber:String,
    val mPrice:Int
){
    val mPostingDate = Calendar.getInstance()

}