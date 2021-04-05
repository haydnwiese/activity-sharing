package com.example.activitysharing.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory(private val providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return requireNotNull(providerMap[modelClass as Class<out ViewModel>]).get() as T
    }
}