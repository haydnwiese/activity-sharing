package com.example.activitysharing.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitysharing.data.network.EventService
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.repository.EventsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val eventsRepository: EventsRepository) :
    ViewModel() {

    val upcomingEvents: LiveData<List<Event>>
        get() = eventsRepository.events

    init {
        refreshUpcomingEvents()
    }

    fun refreshUpcomingEvents(): LiveData<Boolean> {
        return eventsRepository.refreshEvents()
    }
}