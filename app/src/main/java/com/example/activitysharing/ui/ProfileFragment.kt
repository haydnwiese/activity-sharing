package com.example.activitysharing.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.activitysharing.R
import com.example.activitysharing.databinding.FragmentHomeBinding
import com.example.activitysharing.databinding.FragmentProfileBinding
import com.example.activitysharing.ui.common.adapters.EventAdapter
import com.example.activitysharing.ui.home.HomeViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }
}