package com.example.kidedvisor.search.ui.user_search.result_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidedvisor.databinding.ItemZeroSearchAdBinding
import com.example.kidedvisor.search.presenter.models.ResultSearchRVItem

class AdInResultViewHolder(
    private val binding: ItemZeroSearchAdBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ResultSearchRVItem.AdResult) {
        Glide.with(binding.root)
            .load(item.clubInSearch.image)
            .into(binding.imageAdBanner)
    }

    companion object {
        fun newInstance(parent: ViewGroup): AdInResultViewHolder {
            return AdInResultViewHolder(
                ItemZeroSearchAdBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}