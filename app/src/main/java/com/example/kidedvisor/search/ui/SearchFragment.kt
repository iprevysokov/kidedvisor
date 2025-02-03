package com.example.kidedvisor.search.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.kidedvisor.databinding.FragmentSearchBinding
import com.example.kidedvisor.search.presenter.SearchScreenState
import com.example.kidedvisor.search.presenter.SearchViewModel
import com.example.kidedvisor.search.ui.start_search.HeaderFirstItemDecoration
import com.example.kidedvisor.search.ui.start_search.StartSearchAdapter
import com.example.kidedvisor.search.ui.zero_search.FilterTagAdapter
import com.example.kidedvisor.search.ui.zero_search.ZeroSearchAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val zeroSearchAdapter = ZeroSearchAdapter()
    private val filterTagAdapter = FilterTagAdapter()
    private val startSearchAdapter = StartSearchAdapter()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SearchViewModel>()

    private var inputSearchText = DEF_TEXT

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner) { state ->
            render(state)

        }

        binding.editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && inputSearchText.isEmpty()) viewModel.renderStartSearch()
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                inputSearchText = p0.toString()

                if (inputSearchText.isEmpty()) viewModel.renderStartSearch()
                else if (inputSearchText.isNotEmpty()) viewModel.searchDebounce(inputSearchText)
            }

            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
            }
        }

        binding.editText.addTextChangedListener(textWatcher)
    }

    private fun render(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.ZeroSearchState -> renderZeroSearchScreenState(state)
            is SearchScreenState.StartSearchState -> renderStartSearchScreenState(state)
        }
    }

    private fun renderZeroSearchScreenState(state: SearchScreenState.ZeroSearchState) {
        visibilityZeroSearch(true)
        visibilityStartSearch(false)

        showFilterTags(state.branches)

        zeroSearchAdapter.items = state.zeroSearchRVItem
        binding.clubCollectionRecycler.adapter = zeroSearchAdapter
    }

    private fun renderStartSearchScreenState(state: SearchScreenState.StartSearchState) {
        visibilityZeroSearch(false)
        visibilityStartSearch(true)

        startSearchAdapter.items = state.searchStartRVItems
        binding.startSearchRecycler.adapter = startSearchAdapter
        binding.startSearchRecycler.addItemDecoration(HeaderFirstItemDecoration())
    }

    private fun visibilityZeroSearch(visibility: Boolean) {
        binding.apply {
            filterTagRecycler.isVisible = visibility
            filterSearch.isVisible = visibility
            searchAction.isVisible = visibility
            clubCollectionRecycler.isVisible = visibility
        }
    }

    private fun visibilityStartSearch(visibility: Boolean) {
        binding.apply {
            closeSearchAction.isVisible = visibility
            startSearchRecycler.isVisible = visibility
        }
    }

    private fun showFilterTags(tags: List<String>) {
        filterTagAdapter.filterTagList = tags
        binding.filterTagRecycler.adapter = filterTagAdapter
    }

    companion object {
        private const val DEF_TEXT = ""
    }
}