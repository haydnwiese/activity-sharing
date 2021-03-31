package com.example.activitysharing.ui.common.view_holders

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.databinding.ListItemEventBinding

class EventViewHolder(private val binding: ListItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Event) {
        binding.run {
            eventTitle.text = item.eventName
            Glide.with(root)
                .load(item.displayImageUrl)
                .into(headerImage)
        }
    }
}