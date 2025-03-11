package com.example.kidedvisor.search.ui.zero_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.kidedvisor.R
import com.example.kidedvisor.club.presentation.ui.fragment.ClubFragmentDirections
import com.example.kidedvisor.databinding.FragmentZeroSearchBinding
import com.example.kidedvisor.search.presenter.zero_search.ZeroSearchState
import com.example.kidedvisor.search.presenter.zero_search.ZeroSearchViewModel
import com.example.kidedvisor.search.ui.SearchFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class ZeroSearchFragment : Fragment() {

    private val viewModel by viewModel<ZeroSearchViewModel>()

    private var _binding: FragmentZeroSearchBinding? = null
    private val binding get() = _binding!!

    private val filterTagAdapter = FilterTagAdapter()
    private val zeroSearchAdapter = ZeroSearchAdapter { clubId ->
        Navigation.findNavController(requireActivity(), R.id.container_view)
            .navigate(SearchFragmentDirections.actionSearchFragmentToClubFragment(clubId))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentZeroSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.zeroSearchState.observe(viewLifecycleOwner) { state ->
            render(state)
        }

        // переход на экран пользовательского поиска
        binding.editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) findNavController().navigate(
                R.id.action_zeroSearchFragment_to_startSearchFragment
            )
        }

        // переход на экран фильтров
        binding.filterSearch.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.container_view)
                .navigate(R.id.action_searchFragment_to_filtersFragment)
        }
    }

    private fun renderZeroSearchScreenState(state: ZeroSearchState.ZeroSearch) {
        showFilterTags(state.branches)

        zeroSearchAdapter.items = state.zeroSearchRVItem
        binding.clubCollectionRecycler.adapter = zeroSearchAdapter
    }

    private fun render(state: ZeroSearchState) {
        when (state) {
            is ZeroSearchState.ZeroSearch -> renderZeroSearchScreenState(state)
        }
    }

    private fun showFilterTags(tags: List<String>) {
        filterTagAdapter.filterTagList = tags
        binding.filterTagRecycler.adapter = filterTagAdapter
    }
}