package com.example.activitysharing.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.activitysharing.R
import com.example.activitysharing.data.model.Event
import com.example.activitysharing.databinding.FragmentHomeBinding
import com.example.activitysharing.ui.common.adapters.EventAdapter

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private val eventAdapter = EventAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.recyclerView.adapter = eventAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.upcomingEvents.observe(viewLifecycleOwner, { events ->
            eventAdapter.submitList(events.toMutableList())
        })
    }
}