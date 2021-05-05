package com.example.activitysharing.ui.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.activitysharing.ActivitySharingApp
import com.example.activitysharing.R
import com.example.activitysharing.databinding.FragmentHomeBinding
import com.example.activitysharing.databinding.FragmentProfileBinding
import com.example.activitysharing.ui.common.ViewModelFactory
import com.example.activitysharing.ui.common.adapters.EventAdapter
import com.example.activitysharing.ui.home.HomeViewModel
import timber.log.Timber
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        (context.applicationContext as ActivitySharingApp).appComp().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.userProfileDetails.observe(viewLifecycleOwner) { profileDetails ->
            Timber.d(profileDetails.toString())
        }
    }
}