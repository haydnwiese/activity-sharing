package com.example.activitysharing.data.network

import com.example.activitysharing.data.network.model.NetworkUserProfile
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET(NetworkPaths.userProfile)
    suspend fun fetchUserProfile(@Path(NetworkPaths.userIdParam) userId: String): List<NetworkUserProfile>
}