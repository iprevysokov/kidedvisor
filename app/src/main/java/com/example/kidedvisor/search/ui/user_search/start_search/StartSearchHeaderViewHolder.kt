package com.example.kidedvisor.search.ui.user_search.start_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemSearchStartHeaderBinding
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem

class StartSearchHeaderViewHolder(
    private val binding: ItemSearchStartHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchStartRVItem.Header) {
        val headerText = binding.root.context.getText(item.headerText)
        binding.startSearchHeader.text = headerText
    }

    companion object {
        fun newInstance(parent: ViewGroup): StartSearchHeaderViewHolder {
            return StartSearchHeaderViewHolder(
                ItemSearchStartHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}