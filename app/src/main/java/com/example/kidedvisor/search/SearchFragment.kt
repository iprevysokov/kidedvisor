package com.example.kidedvisor.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kidedvisor.databinding.FragmentSearchBinding
import com.example.kidedvisor.search.presenter.OuterAdapter
import com.example.kidedvisor.search.presenter.models.OuterModel

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    val list = listOf(
        OuterModel("Спорт", listOf("Tutti", "Tutti", "Tutti", "Tutti")),
        OuterModel("Спорт", listOf("Tutti", "Tutti", "Tutti", "Tutti")),
        OuterModel("Спорт", listOf("Tutti", "Tutti", "Tutti", "Tutti")),
    )

    private val adapter = OuterAdapter()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

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

        adapter.itemList = list
        binding.clubCollectionRecycler.adapter = adapter
    }
}