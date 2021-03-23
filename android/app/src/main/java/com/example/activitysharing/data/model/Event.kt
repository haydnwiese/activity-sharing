package com.example.activitysharing.data.model

import androidx.recyclerview.widget.DiffUtil

data class Event(
    val id: Long,
    val name: String,
    val displayImage: String,
    val timeCreated: Long,
    val eventTime: Long,
    val createdBy: Long,
    val usersAttendingPreviewUrls: List<String>
)

object EventDiffCallBack: DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
}