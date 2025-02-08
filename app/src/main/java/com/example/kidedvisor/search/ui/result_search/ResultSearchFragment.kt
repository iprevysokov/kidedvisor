package com.example.kidedvisor.search.ui.result_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kidedvisor.R
import com.example.kidedvisor.databinding.FragmentResultSearchBinding
import com.example.kidedvisor.databinding.FragmentStartSearchBinding

class ResultSearchFragment : Fragment() {

    private var _binding: FragmentResultSearchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultSearchBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_result_search, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}