package com.example.kidedvisor.search.ui.zero_search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.kidedvisor.R
import com.example.kidedvisor.search.presenter.models.ZeroSearchRVItem

class ZeroSearchAdapter : RecyclerView.Adapter<ViewHolder>() {

    var items = emptyList<ZeroSearchRVItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            R.layout.item_zero_search_ad -> AdViewHolder.newInstance(parent)
            R.layout.item_zero_search_selection -> ClubsSelectionViewHolder.newInstance(parent)
            else -> error("Unknown viewType create [$viewType]")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.item_zero_search_ad -> {
                val adHolder = holder as AdViewHolder
                val item = items[position] as ZeroSearchRVItem.AdBannerItem
                adHolder.bind(item)
            }
            R.layout.item_zero_search_selection -> {
                val selectionHolder = holder as ClubsSelectionViewHolder
                val item = items[position] as ZeroSearchRVItem.ClubSelectionItem
                selectionHolder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ZeroSearchRVItem.AdBannerItem -> R.layout.item_zero_search_ad
            is ZeroSearchRVItem.ClubSelectionItem -> R.layout.item_zero_search_selection
        }
    }
}