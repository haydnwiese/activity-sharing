package com.example.activitysharing.ui.profile

import androidx.lifecycle.*
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.network.model.NetworkUserProfile
import com.example.activitysharing.data.repository.EventsRepository
import com.example.activitysharing.data.repository.UserRepository
import kotlinx.coroutines.CoroutineExceptionHandler

class ProfileViewModel(
    private val eventsRepository: EventsRepository,
    private val userRepository: UserRepository
): ViewModel() {

    val userProfileDetails: LiveData<NetworkUserProfile>
        get() = retrieveUserProfileDetails()

    fun retrieveEventsForUser(userId: Long) = liveData {
        val userEvents = eventsRepository.getEventsForUser(userId)
        emit(userEvents)
    }

    private fun retrieveUserProfileDetails(): LiveData<NetworkUserProfile> {
        val errorHandler = CoroutineExceptionHandler { _, _ ->
//            _refreshStatus.postValue(false)
//            _networkErrorMessage.postValue("Network Error")
        }
        return liveData(errorHandler) {
            val userProfileDetails = userRepository.getUserProfileDetails(2)
            emit(userProfileDetails)
        }
    }
}