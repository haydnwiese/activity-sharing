package com.example.activitysharing.ui.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.data.domain.EventDiffCallBack
import com.example.activitysharing.databinding.ListItemEventBinding
import com.example.activitysharing.ui.common.view_holders.EventViewHolder

class EventAdapter: ListAdapter<Event, EventViewHolder>(EventDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ListItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}