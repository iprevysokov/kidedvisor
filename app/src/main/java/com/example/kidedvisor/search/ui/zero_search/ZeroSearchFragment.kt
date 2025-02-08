package com.example.kidedvisor.search.ui.zero_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.kidedvisor.R
import com.example.kidedvisor.databinding.FragmentZeroSearchBinding
import com.example.kidedvisor.search.presenter.SearchScreenState
import com.example.kidedvisor.search.presenter.SearchViewModel
import com.example.kidedvisor.search.presenter.ZeroSearchViewModel
import com.example.kidedvisor.search.ui.start_search.StartSearchFragment
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ZeroSearchFragment : Fragment() {

//    private val viewModel by viewModel<ZeroSearchViewModel>()
private val viewModel: SearchViewModel by koinNavGraphViewModel(R.id.search_nav_graph)
    private var _binding: FragmentZeroSearchBinding? = null
    private val binding get() = _binding!!

    private val filterTagAdapter = FilterTagAdapter()
    private val zeroSearchAdapter = ZeroSearchAdapter()

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

        viewModel.getState().observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchScreenState.ZeroSearchState -> renderZeroSearchScreenState(state)
                else -> error("ZeroSearchFragment Error")
            }
        }

        binding.editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) findNavController().navigate(
                R.id.action_zeroSearchFragment_to_startSearchFragment
            )
        }
    }

    private fun renderZeroSearchScreenState(state: SearchScreenState.ZeroSearchState) {
        showFilterTags(state.branches)

        zeroSearchAdapter.items = state.zeroSearchRVItem
        binding.clubCollectionRecycler.adapter = zeroSearchAdapter
    }

    private fun showFilterTags(tags: List<String>) {
        filterTagAdapter.filterTagList = tags
        binding.filterTagRecycler.adapter = filterTagAdapter
    }

    companion object {
        fun newInstance(): ZeroSearchFragment {
            return ZeroSearchFragment()
        }
    }
}