package com.example.activitysharing.data.network.model

import com.example.activitysharing.data.database.model.DatabaseEvent
import com.example.activitysharing.data.database.model.EventUserDisplayImage
import com.example.activitysharing.data.database.model.EventWithUserImages
import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkEvent(
        val id: Long,
        @SerializedName("title") val eventName: String,
        @SerializedName("remoteImageId") val displayImageUrl: String?,
        @SerializedName("createdAt") val timeCreated: Date,
        @SerializedName("scheduledAt") val eventTime: Date,
        @SerializedName("creatorId") val createdBy: Long,
        @SerializedName("attendeeCount") val numberAttending: Int,
        @SerializedName("userDisplayImageUrls") val usersAttendingPreviewUrls: List<String>
)

// TODO: Find a better way to do this
fun List<NetworkEvent>.asDatabaseModel(): List<EventWithUserImages> {
    return map { networkEvent ->
        val eventUserDisplayImages = networkEvent.usersAttendingPreviewUrls.map { url ->
            EventUserDisplayImage(
                id = 0,
                eventId = networkEvent.id,
                imageUrl = url
            )
        }
        EventWithUserImages(
            DatabaseEvent(
                id = networkEvent.id,
                eventName = networkEvent.eventName,
                displayImageUrl = networkEvent.displayImageUrl,
                timeCreated = networkEvent.timeCreated,
                eventTime = networkEvent.eventTime,
                createdBy = networkEvent.createdBy,
                numberAttending = networkEvent.numberAttending
            ),
            eventUserDisplayImages
        )
    }
}