package com.example.kidedvisor.search.presenter.filters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemBranchFilterBinding

class FilterBranchAdapter(
    private val branches: List<String>
) : RecyclerView.Adapter<FilterBranchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterBranchViewHolder {
        val binding = ItemBranchFilterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FilterBranchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return branches.size
    }

    override fun onBindViewHolder(holder: FilterBranchViewHolder, position: Int) {
        holder.bind(branches[position])
    }
}