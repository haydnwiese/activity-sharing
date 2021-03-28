package com.example.activitysharing.di

import androidx.lifecycle.ViewModel
import com.example.activitysharing.data.database.AppDatabase
import com.example.activitysharing.ui.common.ViewModelFactory
import com.example.activitysharing.ui.home.HomeViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import java.lang.annotation.RetentionPolicy
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
class ViewModelModule {

    @MapKey
    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ViewModelKey(
        val value: KClass<out ViewModel>
    )

    @Provides
    fun providesViewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun providesHomeViewModel(): ViewModel {
        return HomeViewModel()
    }
}