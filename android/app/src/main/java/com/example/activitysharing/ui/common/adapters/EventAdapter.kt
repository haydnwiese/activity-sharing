package com.example.activitysharing.ui.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import com.example.activitysharing.data.model.Event
import com.example.activitysharing.databinding.ListItemEventBinding
import com.example.activitysharing.ui.common.view_holders.EventViewHolder

class EventAdapter(private val dataSet: List<Event>): RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ListItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}