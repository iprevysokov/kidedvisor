package com.example.kidedvisor.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kidedvisor.R
import com.example.kidedvisor.databinding.FragmentSearchBinding
import com.example.kidedvisor.search.presenter.FilterTagAdapter
import com.example.kidedvisor.search.presenter.OuterAdapter
import com.example.kidedvisor.search.presenter.models.OuterModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val adapter = OuterAdapter()
    private val filterTagAdapter = FilterTagAdapter()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SearchViewModel>()

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
    }

    private fun render(state: SearchScreenState) {
        when (state) {
            is SearchScreenState.ZeroSearchState -> renderZeroSearchScreenState(state)
        }
    }

    private fun renderZeroSearchScreenState(state: SearchScreenState.ZeroSearchState) {
        showFilterTags(state.outerModels.map {
            it.branchName
        })
        showClubsSlider(state.outerModels)
        showAddBanner()
    }

    private fun showFilterTags(tags: List<String>) {
        binding.filterTagRecycler.visibility = View.VISIBLE
        filterTagAdapter.filterTagList = tags
        binding.filterTagRecycler.adapter = filterTagAdapter
    }

    private fun showClubsSlider(outerModels: List<OuterModel>) {
        adapter.itemList = outerModels
        binding.clubCollectionRecycler.adapter = adapter
    }

    private fun showAddBanner() {
        binding.adBanner.visibility = View.VISIBLE
        Glide.with(requireContext())
            .load(R.drawable.banner_main)
            .into(binding.imageAddBanner)
    }
}