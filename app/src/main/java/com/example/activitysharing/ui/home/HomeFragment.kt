package com.example.activitysharing.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.activitysharing.ActivitySharingApp
import com.example.activitysharing.databinding.FragmentHomeBinding
import com.example.activitysharing.ui.common.ViewModelFactory
import com.example.activitysharing.ui.common.adapters.EventAdapter
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var eventAdapter: EventAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        (context.applicationContext as ActivitySharingApp).appComp().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        eventAdapter = EventAdapter(Glide.with(this))
        binding.recyclerView.adapter = eventAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        binding.swipeContainer.setOnRefreshListener {
            viewModel.refreshUpcomingEvents()
        }
    }

    private fun initObservers() {
        viewModel.refreshStatus.observe(viewLifecycleOwner) { isRefreshing ->
            if (binding.swipeContainer.isRefreshing && !isRefreshing) {
                binding.swipeContainer.isRefreshing = false
            }
        }

        viewModel.upcomingEvents.observe(viewLifecycleOwner, { events ->
            eventAdapter.submitList(events.toMutableList())
        })
    }
}