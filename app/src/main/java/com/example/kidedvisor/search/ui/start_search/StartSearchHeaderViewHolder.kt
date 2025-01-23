package com.example.kidedvisor.search.ui.start_search

import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemSearchStartHeaderBinding

class StartSearchHeaderViewHolder(
    private val binding: ItemSearchStartHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(headerText: String) {
        binding.startSearchHeader.text = headerText
    }
}