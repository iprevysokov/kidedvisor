package com.example.kidedvisor.search.ui.start_search

import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemSearchStartRequestBinding

class StartSearchRequestViewHolder(
    private val binding: ItemSearchStartRequestBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(requestText: String) {
        binding.requestText.text = requestText
    }
}