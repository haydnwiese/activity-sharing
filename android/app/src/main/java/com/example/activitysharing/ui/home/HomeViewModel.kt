package com.example.activitysharing.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitysharing.data.network.EventService
import com.example.activitysharing.data.domain.Event
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _upcomingEvents = MutableLiveData<List<Event>>()
    val upcomingEvents: LiveData<List<Event>>
        get() = _upcomingEvents

    init {
        fetchUpcomingEvents()
    }

    fun fetchUpcomingEvents() {
        viewModelScope.launch {
            val events = EventService().fetchUpcomingEvents("haydn")
            _upcomingEvents.value = events
        }
    }
}