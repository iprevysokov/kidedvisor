package com.example.kidedvisor.search.ui.zero_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidedvisor.databinding.ItemZeroSearchAdBinding
import com.example.kidedvisor.search.presenter.models.ZeroSearchRVItem

class AdViewHolder(
    private val binding: ItemZeroSearchAdBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ZeroSearchRVItem.AdBannerItem) {
        Glide.with(binding.root)
            .load(item.adBanner.poster)
            .into(binding.imageAdBanner)
    }

    companion object {
        fun newInstance(parent: ViewGroup): AdViewHolder {
            return AdViewHolder(
                ItemZeroSearchAdBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}