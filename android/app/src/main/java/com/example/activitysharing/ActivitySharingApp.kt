package com.example.activitysharing

import android.app.Application
import com.example.activitysharing.di.AppComponent
import com.example.activitysharing.di.DaggerAppComponent

class ActivitySharingApp: Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    fun appComp() = appComponent
}