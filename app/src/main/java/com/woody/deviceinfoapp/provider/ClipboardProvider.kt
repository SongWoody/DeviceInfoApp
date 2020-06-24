package com.woody.deviceinfoapp.provider

interface ClipboardProvider {
    fun clip(strLabel: String, strCopy: String) : Boolean
}