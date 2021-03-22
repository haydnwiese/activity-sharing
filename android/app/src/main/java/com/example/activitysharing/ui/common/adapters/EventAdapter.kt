package com.example.activitysharing.ui.common.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import com.example.activitysharing.data.model.Event
import com.example.activitysharing.data.model.EventDiffCallBack
import com.example.activitysharing.databinding.ListItemEventBinding
import com.example.activitysharing.ui.common.view_holders.EventViewHolder

class EventAdapter: ListAdapter<Event, EventViewHolder>(EventDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ListItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("EventAdapter", "Create viewholder")
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}