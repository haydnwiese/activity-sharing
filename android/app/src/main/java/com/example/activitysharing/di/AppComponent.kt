package com.example.activitysharing.di

import android.app.Application
import com.example.activitysharing.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RoomModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: HomeFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}