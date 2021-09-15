package com.bala.nytnews

import android.app.Application
import android.content.Context

class NytApplication : Application() {

    override fun onCreate() {
        context = this
        super.onCreate()
    }
    companion object
    {
        lateinit var context: Context
    }
}