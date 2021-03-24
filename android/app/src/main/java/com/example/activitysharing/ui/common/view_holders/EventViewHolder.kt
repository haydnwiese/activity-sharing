package com.example.activitysharing.ui.common.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.databinding.ListItemEventBinding

class EventViewHolder(private val binding: ListItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Event) {
        binding.run {
            eventTitle.text = item.name
        }
    }
}