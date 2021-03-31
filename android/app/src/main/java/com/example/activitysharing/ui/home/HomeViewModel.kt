package com.example.activitysharing.ui.home

import androidx.lifecycle.*
import com.example.activitysharing.data.network.EventService
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.repository.EventsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val eventsRepository: EventsRepository) :
    ViewModel() {

    val upcomingEvents: LiveData<List<Event>>
        get() = eventsRepository.events

    val refreshStatus: LiveData<Boolean>
        get() = eventsRepository.refreshStatus.asLiveData()

    init {
        refreshUpcomingEvents()
    }

    fun refreshUpcomingEvents() {
        viewModelScope.launch {
            eventsRepository.refreshEvents()
        }
    }
}