package com.example.kidedvisor.search.ui.start_search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kidedvisor.R
import com.example.kidedvisor.databinding.FragmentStartSearchBinding
import com.example.kidedvisor.search.presenter.SearchScreenState
import com.example.kidedvisor.search.presenter.SearchViewModel
import com.example.kidedvisor.search.presenter.StartSearchViewModel
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartSearchFragment : Fragment() {

    private var _binding: FragmentStartSearchBinding? = null
    private val binding get() = _binding!!

//    private val viewModel by viewModel<StartSearchViewModel>()
    private val viewModel: SearchViewModel by koinNavGraphViewModel(R.id.search_nav_graph)

    private val startSearchAdapter = StartSearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartSearchBinding.inflate(inflater, container, false)

        viewModel.renderStartSearch()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchScreenState.StartSearchState -> renderStartSearchScreenState(state)
                else -> {
                    Log.d("MyTag", "onViewCreated: ${state.toString()}")
                    error("Wrong State")
                }
            }
        }
//        viewModel.startSearchState.observe(viewLifecycleOwner) { state ->
//            when (state) {
//                is SearchScreenState.StartSearchState -> renderStartSearchScreenState(state)
//                else -> error("Wrong State")
//            }
//        }

        binding.editText.requestFocus()

        binding.closeSearchAction.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun renderStartSearchScreenState(state: SearchScreenState.StartSearchState) {
        startSearchAdapter.items = state.searchStartRVItems
        binding.startSearchRecycler.adapter = startSearchAdapter
        binding.startSearchRecycler.addItemDecoration(HeaderFirstItemDecoration())
    }
}