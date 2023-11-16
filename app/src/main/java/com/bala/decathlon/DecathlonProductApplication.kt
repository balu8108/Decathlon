package com.bala.decathlon

import android.app.Application

class DecathlonProductApplication : Application() {

    override fun onCreate() {
        application = this
        super.onCreate()
    }
    companion object
    {
        lateinit var application: Application
    }
}