package com.example.activitysharing.data.repository

import com.example.activitysharing.data.network.UserService
import com.example.activitysharing.data.network.model.NetworkUserProfile
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class UserRepository(private val userService: UserService) {

    suspend fun getUserProfileDetails(userId: Long): NetworkUserProfile {
        return userService.fetchUserProfile(userId)
    }
}