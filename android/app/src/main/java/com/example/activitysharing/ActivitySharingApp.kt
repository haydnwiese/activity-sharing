package com.example.activitysharing

import android.app.Application
import com.example.activitysharing.di.component.AppComponent
import com.example.activitysharing.di.component.DaggerAppComponent
import timber.log.Timber

class ActivitySharingApp: Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    fun appComp() = appComponent
}