package com.example.kidedvisor.search.ui.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.kidedvisor.databinding.FragmentFiltersBinding
import com.example.kidedvisor.search.presenter.filters.FilterBranchAdapter
import com.example.kidedvisor.search.presenter.filters.FiltersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FiltersFragment : Fragment() {

    private val viewModel: FiltersViewModel by viewModel()

    private var _binding: FragmentFiltersBinding? = null
    private val binding: FragmentFiltersBinding get() = _binding!!

    private var minAge = DEF_MIN_AGE
    private var maxAge = DEF_MAX_AGE
    private var minRating = DEF_MIN_RATING
    private var maxRating = DEF_MAX_RATING

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiltersBinding.inflate(inflater, container, false)

        setAgeFilter(minAge, maxAge)
        setRatingFilter(minRating, maxRating)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.branchRecycler.adapter = FilterBranchAdapter(
            listOf("Спорт", "Наука", "Искусство")
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setAgeFilter(minAge: String, maxAge: String) {
        binding.apply {
            minAgeEditText.hint = minAge
            minAgeInputLayout.placeholderText = minAge
            maxAgeEditText.hint = maxAge
            maxAgeInputLayout.placeholderText = maxAge
        }
    }

    private fun setRatingFilter(minRating: String, maxRating: String) {
        binding.apply {
            minRatingEditText.hint = minRating
            minRatingInputLayout.placeholderText = minRating
            maxRatingEditText.hint = maxRating
            maxRatingInputLayout.placeholderText = maxRating
        }
    }

    companion object {
        private const val DEF_MIN_AGE = "4"
        private const val DEF_MAX_AGE = "16"
        private const val DEF_MIN_RATING = "0"
        private const val DEF_MAX_RATING = "5"
    }
}