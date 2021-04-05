package com.example.activitysharing.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.repository.EventsRepository
import com.example.activitysharing.util.GREETING_AFTERNOON
import com.example.activitysharing.util.GREETING_EVENING
import com.example.activitysharing.util.GREETING_HELLO
import com.example.activitysharing.util.GREETING_MORNING
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
            in 0..12 -> GREETING_MORNING
            in 13..16 -> GREETING_AFTERNOON
            in 17..23 -> GREETING_EVENING
            else -> GREETING_HELLO
        }
}