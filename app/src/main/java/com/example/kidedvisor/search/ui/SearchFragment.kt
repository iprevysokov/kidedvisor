package com.example.kidedvisor.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kidedvisor.databinding.FragmentSearchBinding
import com.example.kidedvisor.search.presenter.OuterAdapter
import com.example.kidedvisor.search.presenter.models.OuterModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val adapter = OuterAdapter()

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
            is SearchScreenState.ZeroSearchState -> {
                showZeroSearchScreenState(state.outerModels)
            }
        }
    }

    private fun showZeroSearchScreenState(outerModels: List<OuterModel>) {
        adapter.itemList = outerModels
        binding.clubCollectionRecycler.adapter = adapter
    }
}