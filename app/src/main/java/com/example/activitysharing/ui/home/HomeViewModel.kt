package com.example.activitysharing.ui.home

import androidx.lifecycle.*
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.repository.EventsRepository
import com.example.activitysharing.util.GREETING_AFTERNOON
import com.example.activitysharing.util.GREETING_EVENING
import com.example.activitysharing.util.GREETING_HELLO
import com.example.activitysharing.util.GREETING_MORNING
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val eventsRepository: EventsRepository) :
    ViewModel() {

    val userFirstName = "Haydn"
    val timeBasedGreeting: String
        get() = when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..12 -> GREETING_MORNING
            in 13..16 -> GREETING_AFTERNOON
            in 17..23 -> GREETING_EVENING
            else -> GREETING_HELLO
        }

    private val _refreshStatus = MutableLiveData(false)
    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus

    private val _networkErrorMessage = MutableLiveData<String>()
    val networkErrorMessage: LiveData<String>
        get() = _networkErrorMessage

    val upcomingEvents: LiveData<List<Event>>
        get() = eventsRepository.events.onEach { _refreshStatus.value = false }.asLiveData()

    init {
        refreshUpcomingEvents()
    }

    fun refreshUpcomingEvents() {
        val errorHandler = CoroutineExceptionHandler { context, error ->
            _refreshStatus.postValue(false)
            _networkErrorMessage.postValue("Network Error")
        }
        _refreshStatus.value = true
        viewModelScope.launch(errorHandler) {
            eventsRepository.refreshEvents()
        }
    }
}