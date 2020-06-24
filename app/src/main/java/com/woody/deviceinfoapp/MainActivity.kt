package com.woody.deviceinfoapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.woody.deviceinfoapp.databinding.ActivityMainBinding
import com.woody.deviceinfoapp.provider.ClipboardProvider

class MainActivity : AppCompatActivity(),
    ClipboardProvider {
    private lateinit var mDataBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mDataBinding.lifecycleOwner = this

        val viewModel : MainViewModel = ViewModelProviders.of(this, MainViewModelFactory(this)).get(MainViewModel::class.java)
        mDataBinding.viewModel = viewModel
    }

    override fun clip(strLabel: String, strCopy: String) : Boolean {
        val clipboardManager = this.getSystemService(Context.CLIPBOARD_SERVICE)
        if (clipboardManager is ClipboardManager) {
            val clipData = ClipData.newPlainText(strLabel, strCopy)
            clipboardManager.setPrimaryClip(clipData)
            return true
        }
        return false
    }
}
