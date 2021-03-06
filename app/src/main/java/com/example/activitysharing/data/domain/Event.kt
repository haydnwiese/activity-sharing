package com.example.activitysharing.data.domain

import androidx.recyclerview.widget.DiffUtil
import java.util.*

data class Event(
        val id: Long,
        val eventName: String,
        val displayImageUrl: String?,
        val timeCreated: Date,
        val eventTime: Date,
        val createdBy: Long,
        val numberAttending: Int,
        val usersAttendingPreviewUrls: List<String>
)

object EventDiffCallBack: DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
}