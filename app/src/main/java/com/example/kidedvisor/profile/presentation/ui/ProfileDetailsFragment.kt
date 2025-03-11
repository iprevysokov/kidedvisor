package com.example.kidedvisor.profile.presentation.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kidedvisor.R
import com.example.kidedvisor.databinding.FragmentProfileDetailsBinding
import com.example.kidedvisor.profile.presentation.viewModel.ProfileDetailsViewModel

class ProfileDetailsFragment : Fragment() {
    private var  _binding : FragmentProfileDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarProfileDetails.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.personalAccount.setOnClickListener {
            findNavController().navigate(R.id.action_profileDetailsFragment_to_profileEditFragment)
        }
    }
}