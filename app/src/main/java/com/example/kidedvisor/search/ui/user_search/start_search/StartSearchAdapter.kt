package com.example.kidedvisor.search.ui.user_search.start_search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.R
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem

class StartSearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<SearchStartRVItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_search_start_header -> StartSearchHeaderViewHolder.newInstance(parent)
            R.layout.item_search_start_club -> StartSearchClubViewHolder.newInstance(parent)
            R.layout.item_search_start_request -> StartSearchRequestViewHolder.newInstance(parent)
            else -> error("Unknown viewType create [$viewType]")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.item_search_start_header -> {
                val headerHolder = holder as StartSearchHeaderViewHolder
                val item = items[position] as SearchStartRVItem.Header
                headerHolder.bind(item)
            }
            R.layout.item_search_start_club -> {
                val clubHolder = holder as StartSearchClubViewHolder
                val item = items[position] as SearchStartRVItem.Club
                clubHolder.bind(item)
            }
            R.layout.item_search_start_request -> {
                val requestHolder = holder as StartSearchRequestViewHolder
                val item = items[position] as SearchStartRVItem.Request
                requestHolder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SearchStartRVItem.Header -> R.layout.item_search_start_header
            is SearchStartRVItem.Club -> R.layout.item_search_start_club
            is SearchStartRVItem.Request -> R.layout.item_search_start_request
        }
    }
}