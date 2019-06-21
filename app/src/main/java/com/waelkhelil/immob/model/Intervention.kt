package com.waelkhelil.immob.model

import java.util.*


data class Intervention(
    val mNumber: Int,
    val mType:InterventionType,
    val mPlumberName:String
){
    val mPostingDate:Calendar = Calendar.getInstance(Locale.getDefault())
    init {

    }
    enum class InterventionType{
        TYPE01, TYPE02
    }
}