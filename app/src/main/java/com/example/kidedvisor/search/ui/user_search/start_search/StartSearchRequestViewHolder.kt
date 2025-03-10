package com.example.kidedvisor.search.ui.user_search.start_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemSearchStartRequestBinding
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem

class StartSearchRequestViewHolder(
    private val binding: ItemSearchStartRequestBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchStartRVItem.Request) {
        val requestText = item.requestText
        binding.requestText.text = requestText
    }

    companion object {
        fun newInstance(parent: ViewGroup): StartSearchRequestViewHolder {
            return StartSearchRequestViewHolder(
                ItemSearchStartRequestBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}