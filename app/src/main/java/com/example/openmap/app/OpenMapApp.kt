package com.example.openmap.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OpenMapApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: OpenMapApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}