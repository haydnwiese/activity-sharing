package com.example.activitysharing.di.component

import android.app.Application
import com.example.activitysharing.di.module.AppModule
import com.example.activitysharing.di.module.RetrofitModule
import com.example.activitysharing.di.module.RoomModule
import com.example.activitysharing.di.module.ViewModelModule
import com.example.activitysharing.ui.home.HomeFragment
import com.example.activitysharing.ui.profile.ProfileFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RoomModule::class, ViewModelModule::class, RetrofitModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: HomeFragment)

    fun inject(fragment: ProfileFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}