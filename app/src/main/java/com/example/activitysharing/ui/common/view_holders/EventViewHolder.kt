package com.example.activitysharing.ui.common.view_holders

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.activitysharing.data.domain.Event
import com.example.activitysharing.databinding.ListItemEventBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class EventViewHolder(private val binding: ListItemEventBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Event, glide: RequestManager) {
        bindDetails(item)
        loadImages(item, glide)
    }

    private fun bindDetails(item: Event) {
        binding.run {
            eventTitle.text = item.eventName
        }
    }

    // TODO: Maybe optimize i.e. programmatically create views
    private fun loadImages(item: Event, glide: RequestManager) {
        updateImageViewVisibilities(item)

        with(binding) {
            val attendeeImageViews = listOf(attendeeImageView1, attendeeImageView2, blurredAttendeeImageView)
            for (i in attendeeImageViews.indices) {
                if (i >= item.usersAttendingPreviewUrls.size) break

                var glideRequest = glide.loadWithOptions(item.usersAttendingPreviewUrls[i])
                if (i == attendeeImageViews.lastIndex)
                    glideRequest = glideRequest.apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))

                glideRequest.into(attendeeImageViews[i])
            }
            item.displayImageUrl?.let { glide.loadWithOptions(it).into(headerImage) }
        }
    }

    private fun updateImageViewVisibilities(item: Event) {
        val numberAttendees = item.numberAttending
        with(binding) {
            headerImage.visibility = if (item.displayImageUrl == null) View.GONE else View.VISIBLE

            when {
                numberAttendees >= 3 -> {
                    attendeeImageView1.visibility = View.VISIBLE
                    attendeeImageView2.visibility = View.VISIBLE
                    blurredAttendeeContainer.visibility = View.VISIBLE
                    attendeeNumberTextView.text = String.format("+%s", numberAttendees - 2)
                }
                numberAttendees == 2 -> {
                    attendeeImageView1.visibility = View.VISIBLE
                    attendeeImageView2.visibility = View.VISIBLE
                    blurredAttendeeContainer.visibility = View.GONE
                }
                numberAttendees == 1 -> {
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

    private fun RequestManager.loadWithOptions(url: String): RequestBuilder<Drawable> {
        return load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    }
}