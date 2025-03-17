package com.example.kidedvisor.club.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.kidedvisor.R
import com.example.kidedvisor.club.domain.models.Club
import com.example.kidedvisor.club.domain.models.ClubIntent
import com.example.kidedvisor.club.domain.models.ClubState
import com.example.kidedvisor.club.presentation.adapter.PhotoClubAdapter
import com.example.kidedvisor.club.presentation.viewModel.ClubViewModel
import com.example.kidedvisor.databinding.FragmentClubBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel


class ClubFragment : Fragment() {
    private var _binding: FragmentClubBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<ClubViewModel>()

    private val args: ClubFragmentArgs by navArgs()

    private lateinit var bottomSheetSeason: BottomSheetBehavior<*>

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

            btContact.setOnClickListener {
                findNavController().navigate(R.id.action_clubFragment_to_connectionFragment)
            }

            tvRequestRights.setOnClickListener {
                findNavController().navigate(R.id.action_clubFragment_to_requestCardRightsFragment)
            }

            bottomSheetSeason = BottomSheetBehavior.from(binding.bottomSheetClub).apply {
                state = BottomSheetBehavior.STATE_HIDDEN
            }

            tvSchedule.setOnClickListener {
                bottomSheetSeason.state = BottomSheetBehavior.STATE_COLLAPSED
                scheduleGroupBottomSheet.isVisible = true
                groupOfSeasonTickets.isVisible = false
            }

            tvCommonOfReviews.setOnClickListener {
                findNavController().navigate(R.id.action_clubFragment_to_feedbacksFragment)
            }

            btSeasonTicket.setOnClickListener {
                bottomSheetSeason.state = BottomSheetBehavior.STATE_COLLAPSED
                scheduleGroupBottomSheet.isVisible = false
                groupOfSeasonTickets.isVisible = true
            }

            overlay.setOnClickListener {
                bottomSheetSeason.state = BottomSheetBehavior.STATE_HIDDEN
            }

            bottomSheetSeason.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            overlay.isVisible = true
                            bottomSheetClub.isVisible = true
                        }

                        BottomSheetBehavior.STATE_HIDDEN -> {
                            overlay.isVisible = false
                            bottomSheetClub.isVisible = false
                            groupOfSeasonTickets.isVisible = false
                            scheduleGroupBottomSheet.isVisible = false
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })

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
            bottomSheetClub.isVisible = false
            overlay.isVisible = false
            progressBar.isVisible = false
            layouts.isVisible = false
            errorGroup.isVisible = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}