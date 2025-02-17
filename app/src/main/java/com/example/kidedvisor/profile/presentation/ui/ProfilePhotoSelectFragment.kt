package com.example.kidedvisor.profile.presentation.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kidedvisor.R
import com.example.kidedvisor.databinding.FragmentProfilePhotoSelectBinding
import com.example.kidedvisor.profile.presentation.viewModel.ProfilePhotoSelectViewModel

class ProfilePhotoSelectFragment : Fragment() {
    private var _binding: FragmentProfilePhotoSelectBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfilePhotoSelectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilePhotoSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}