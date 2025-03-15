package com.example.kidedvisor.club.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kidedvisor.club.domain.models.Club
import com.example.kidedvisor.club.domain.models.ClubIntent
import com.example.kidedvisor.club.domain.models.ClubState
import com.example.kidedvisor.club.presentation.adapter.PhotoClubAdapter
import com.example.kidedvisor.club.presentation.viewModel.ClubViewModel
import com.example.kidedvisor.databinding.FragmentClubBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ClubFragment : Fragment() {
    private var _binding: FragmentClubBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<ClubViewModel>()

    private val args: ClubFragmentArgs by navArgs()

    private val photoAdapter = PhotoClubAdapter {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.processIntent(ClubIntent.LoadClub(args.clubId))
        setupObservers()

        binding.recyclerPhotoClub.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerPhotoClub.adapter = photoAdapter

        binding.toolbarclub.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun setupObservers() {
        viewModel.clubState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ClubState.Success -> successState(state.club)
                is ClubState.Loading -> loadingState()
                is ClubState.Error -> errorState()
            }
        }
    }

    private fun successState(club: Club) {
        binding.apply {
            tvClubName.text = club.name
            overlay.isVisible = false
            tvStreetClub.text = club.address
            progressBar.isVisible = false
            layouts.isVisible = true
            errorGroup.isVisible = false
//            Log.d("Photo Club", club.photo)
        }
        Glide.with(this)
            .load(club.photo)
            .into(binding.imgClub)
    }

    private fun loadingState() {
        binding.apply {
            overlay.isVisible = false
            progressBar.isVisible = true
            layouts.isVisible = false
            errorGroup.isVisible = false
        }
    }

    private fun errorState() {
        binding.apply {
            overlay.isVisible = false
            progressBar.isVisible = false
            layouts.isVisible = false
            errorGroup.isVisible = true
        }
    }

}