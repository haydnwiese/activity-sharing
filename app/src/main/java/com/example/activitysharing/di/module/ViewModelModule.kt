package com.example.activitysharing.di.module

import androidx.lifecycle.ViewModel
import com.example.activitysharing.data.repository.EventsRepository
import com.example.activitysharing.data.repository.UserRepository
import com.example.activitysharing.ui.common.ViewModelFactory
import com.example.activitysharing.ui.home.HomeViewModel
import com.example.activitysharing.ui.profile.ProfileViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
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
    fun providesHomeViewModel(eventsRepository: EventsRepository): ViewModel {
        return HomeViewModel(eventsRepository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun providesProfileViewModel(
        eventsRepository: EventsRepository,
        userRepository: UserRepository
    ): ViewModel {
        return ProfileViewModel(eventsRepository, userRepository)
    }
}