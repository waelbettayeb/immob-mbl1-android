package com.waelkhelil.immob

import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.waelkhelil.immob.model.Intervention
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class MainViewModel : ViewModel() {
    val mIntervention: MutableLiveData<List<Intervention>> = MutableLiveData(listOf())
    private val newIntervention = mutableListOf<Intervention>()
    val fileName = "PostJson.json"

    fun addIntervention(vararg intervention: Intervention){
        newIntervention.addAll(intervention)
        mIntervention.postValue(newIntervention)
//        toJson()
        writeJSONtoFile()
    }
    private fun toJson():String{
        val gson = Gson()
        return gson.toJson(mIntervention.value)
    }
    private fun writeJSONtoFile() {
        val file = File(Environment.getExternalStorageDirectory(),fileName)
        file.printWriter().use { out ->
            out.println(toJson())
        }
    }

}