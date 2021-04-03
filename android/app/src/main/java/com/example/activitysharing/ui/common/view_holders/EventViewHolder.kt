package com.example.activitysharing.ui.common.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.databinding.ListItemEventBinding

class EventViewHolder(private val binding: ListItemEventBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Event, glide: RequestManager) {
        bindDetails(item)
        loadImages(item, glide)
    }

    private fun bindDetails(item: Event) {
        binding.run {
            eventTitle.text = item.eventName
            Glide.with(root)
                .load(item.displayImageUrl)
                .centerCrop()
                .into(headerImage)
        }
    }

    // TODO: Maybe optimize i.e. programmatically create views
    private fun loadImages(item: Event, glide: RequestManager) {
        binding.run {
            when {
                item.numberAttending > 2 -> {
                    attendeeImageView1.visibility = View.VISIBLE
                    attendeeImageView2.visibility = View.VISIBLE
                    blurredAttendeeContainer.visibility = View.VISIBLE
                    attendeeNumberTextView.text = String.format("+%s", item.numberAttending - 2)
                }
                item.numberAttending == 2L -> {
                    attendeeImageView1.visibility = View.VISIBLE
                    attendeeImageView2.visibility = View.VISIBLE
                    blurredAttendeeContainer.visibility = View.GONE
                }
                item.numberAttending == 1L -> {
                    attendeeImageView1.visibility = View.VISIBLE
                    attendeeImageView2.visibility = View.GONE
                    blurredAttendeeContainer.visibility = View.GONE
                }
                else -> {
                    attendeePreviewContainer.visibility = View.INVISIBLE
                }
            }
        }
    }
}