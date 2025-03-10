package com.example.kidedvisor.search.presenter.filters

import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemBranchFilterBinding

class FilterBranchViewHolder(
    private val binding: ItemBranchFilterBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(branch: String) {
        binding.itemBranch.text = branch
    }
}