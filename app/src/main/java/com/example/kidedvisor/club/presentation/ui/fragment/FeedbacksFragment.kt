package com.example.kidedvisor.club.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kidedvisor.R
import com.example.kidedvisor.club.presentation.adapter.FeedbacksAdapter
import com.example.kidedvisor.club.presentation.adapter.PhotoClubAdapter
import com.example.kidedvisor.club.presentation.adapter.clickListener.FeedbacksClickListener
import com.example.kidedvisor.databinding.FragmentFeedbacksBinding

class FeedbacksFragment : Fragment() {

    private var _binding: FragmentFeedbacksBinding? = null
    private val binding get() = _binding!!

    private lateinit var feedbacksAdapter: FeedbacksAdapter
    private lateinit var photoAdapter: PhotoClubAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedbacksBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedbacksAdapter = FeedbacksAdapter(object : FeedbacksClickListener {
            override fun onFeedbacksClick(feedback: String) {
                TODO("Not yet implemented")
            }

            override fun onMoreClick(feedback: String) {
                TODO("Not yet implemented")
            }

            override fun onCommentClick(feedback: String) {
                TODO("Not yet implemented")
            }

        })
        binding.rvFeedbacks.adapter = feedbacksAdapter
        binding.rvFeedbacks.layoutManager = LinearLayoutManager(requireContext())

        binding.recyclerPhotoFeedback.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerPhotoFeedback.adapter = photoAdapter


        val radioButtonsFilter = listOf(
            binding.radioBtNewOnesFirst,
            binding.radioBtFirstHighRating,
            binding.radioBtFirstLowRating
        )

        val radioButtonsClaim = listOf(
            binding.radioBtMisrepresentation,
            binding.radioBtUnacceptableContent,
            binding.radioBtOtherReason
        )

        binding.apply {
            radioBtNewOnesFirst.isChecked = true
            handleFilterSelection(radioBtNewOnesFirst, radioButtonsFilter)

            radioBtNewOnesFirst.setOnClickListener {
                handleFilterSelection(radioBtNewOnesFirst, radioButtonsFilter)
                // Дальнейшая логика обработки выбранного элемента
            }

            radioBtFirstHighRating.setOnClickListener {
                handleFilterSelection(radioBtFirstHighRating, radioButtonsFilter)
                // Дальнейшая логика обработки выбранного элемента
            }

            radioBtFirstLowRating.setOnClickListener {
                handleFilterSelection(radioBtFirstLowRating, radioButtonsFilter)
                // Дальнейшая логика обработки выбранного элемента
            }

            radioBtMisrepresentation.setOnClickListener {
                uncheckRadioButtonFilter(radioBtMisrepresentation, radioButtonsClaim)
                // Дальнейшая логика обработки выбранного элемента
            }

            radioBtUnacceptableContent.setOnClickListener {
                uncheckRadioButtonFilter(radioBtUnacceptableContent, radioButtonsClaim)
                // Дальнейшая логика обработки выбранного элемента
            }

            radioBtOtherReason.setOnClickListener {
                uncheckRadioButtonFilter(radioBtOtherReason, radioButtonsClaim)
                // Дальнейшая логика обработки выбранного элемента
            }

        }


        //прописал сразу видимость всех bottomSheet

        binding.apply {
            filterFeedback.setOnClickListener {
                tvTittleBottomSheet.text = getString(R.string.show_first)
                bottomSheetOverlay.isVisible = true
                btBottomSheetCancel.isVisible = false
                btAndEditTextGroup.isVisible = false
                constraintShowFirst.isVisible = true
                constraintLayoutAction.isVisible = false
            }
//
//            buttonInItem.setOnClickListener {
//                tvTittleBottomSheet.text = getString(R.string.action)
//                bottomSheetOverlay.isVisible = true
//                btBottomSheetCancel.isVisible = true
//                btAndEditTextGroup.isVisible = false
//                constraintShowFirst.isVisible = false
//                constraintLayoutAction.isVisible = true
//                constraintLayoutClaim.isVisible = false
//            }
//
            complain.setOnClickListener {
                tvTittleBottomSheet.text = getString(R.string.claim)
                bottomSheetOverlay.isVisible = true
                btBottomSheetCancel.isVisible = false
                btAndEditTextGroup.isVisible = true
                constraintShowFirst.isVisible = false
                constraintLayoutAction.isVisible = false
                constraintLayoutClaim.isVisible = true
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun uncheckRadioButtonFilter(
        selectedRadioButton: RadioButton,
        radioButtons: List<RadioButton>
    ) {
        for (radioButton in radioButtons) {
            if (radioButton != selectedRadioButton) {
                radioButton.isChecked = false
            }
        }
    }

    private fun handleFilterSelection(
        selectedRadioButton: RadioButton,
        radioButtons: List<RadioButton>
    ) {
        uncheckRadioButtonFilter(selectedRadioButton, radioButtons)
        when (selectedRadioButton.id) {
            R.id.radio_bt_new_ones_first -> {
                binding.descriptionFilter.text = getString(R.string.new_ones_first)
            }

            R.id.radio_bt_first_high_rating -> {
                binding.descriptionFilter.text = getString(R.string.first_high_rating)
            }

            R.id.radio_bt_first_low_rating -> {
                binding.descriptionFilter.text = getString(R.string.first_low_rating)
            }
        }
    }

}