package com.example.activitysharing.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitysharing.data.api.EventService
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    init {
        fetchUpcomingEvents()
    }

    fun fetchUpcomingEvents() {
        viewModelScope.launch {
            val events = EventService().fetchUpcomingEvents("haydn")
            Log.d("HomeViewModel", events.first().name)
        }
    }
}