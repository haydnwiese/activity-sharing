package com.example.activitysharing.ui.home

import androidx.lifecycle.*
import com.example.activitysharing.data.network.EventService
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.repository.EventsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val eventsRepository: EventsRepository) :
    ViewModel() {

    val userFirstName = "Haydn"
    val timeBasedGreeting: String
        get() = getTimeBasedGreetingMessage()

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

    private fun getTimeBasedGreetingMessage(): String =
        when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..12 -> "Good morning"
            in 13..16 -> "Good afternoon"
            in 17..23 -> "Good evening"
            else -> "Hello"
        }
}