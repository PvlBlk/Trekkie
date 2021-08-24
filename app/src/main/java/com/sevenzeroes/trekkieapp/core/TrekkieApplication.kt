package com.sevenzeroes.trekkieapp.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TrekkieApplication : Application() {

    companion object{
        var instance: TrekkieApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}