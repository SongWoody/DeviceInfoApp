package com.woody.deviceinfoapp

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val deviceModel: MutableLiveData<CharSequence> by lazy {
        MutableLiveData<CharSequence>().apply {
            value = Build.DEVICE
        }
    }

    fun getUsers(): LiveData<CharSequence> {
        return deviceModel
    }

    fun clickAction() {
        //todo clip board
    }
}