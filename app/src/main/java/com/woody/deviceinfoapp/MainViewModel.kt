package com.woody.deviceinfoapp

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val deviceBrand: MutableLiveData<CharSequence> by lazy {
        MutableLiveData<CharSequence>().apply {
            value = Build.BRAND
        }
    }

    val deviceModel: MutableLiveData<CharSequence> by lazy {
        MutableLiveData<CharSequence>().apply {
            value = Build.MODEL
        }
    }

    fun clickAction() {
        //todo clip board
    }
}