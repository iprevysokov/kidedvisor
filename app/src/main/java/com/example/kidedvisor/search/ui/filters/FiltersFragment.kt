package com.example.kidedvisor.search.ui.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kidedvisor.R
import com.example.kidedvisor.databinding.FragmentFiltersBinding
import com.example.kidedvisor.search.presenter.filters.FiltersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FiltersFragment : Fragment() {

    private val viewModel: FiltersViewModel by viewModel()

    private var _binding: FragmentFiltersBinding? = null
    private val binding: FragmentFiltersBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}