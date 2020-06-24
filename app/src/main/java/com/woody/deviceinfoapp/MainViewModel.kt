package com.woody.deviceinfoapp

import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.woody.deviceinfoapp.provider.ClipboardProvider


class MainViewModelFactory(private val clipboard: ClipboardProvider): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(clipboard) as T
    }
}

class MainViewModel(private val clipboard : ClipboardProvider) : ViewModel() {
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

    val androidVersionNumber: MutableLiveData<CharSequence> by lazy {
        MutableLiveData<CharSequence>().apply {
            val sdkInt = Build.VERSION.SDK_INT.toString()
            var sdkName : String? = null;
            val fields =
                VERSION_CODES::class.java.fields
            for (field in fields) {
                val fieldName = field.name
                var fieldValue = -1
                try {
                    fieldValue = field.getInt(Any())
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }
                if (fieldValue == Build.VERSION.SDK_INT) {
                    sdkName = fieldName
                }
            }

            value = if (sdkName != null) {
                "$sdkInt($sdkName)"
            } else {
                sdkInt
            }
        }
    }

    fun clickAction() {
        val copyText = "Device Brand: ${deviceBrand.value}\n" +
                "Device Model: ${deviceModel.value}\n" +
                "Android Version: ${androidVersionNumber.value}"
        clipboard.clip("deviceInfo",copyText)
    }
}