package com.example.kidedvisor.search.ui.user_search.result_search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.R
import com.example.kidedvisor.search.presenter.models.ResultSearchRVItem
import java.util.UUID

class ResultSearchAdapter(
    private val onClick: (clubId: UUID) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<ResultSearchRVItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_zero_search_ad -> AdInResultViewHolder.newInstance(parent)
            R.layout.item_search_result -> SearchResultViewHolder.newInstance(parent)
            else -> error("Unknown viewType create [$viewType]")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.item_zero_search_ad -> {
                val adHolder = holder as AdInResultViewHolder
                val item = items[position] as ResultSearchRVItem.AdResult
                adHolder.bind(item)
            }
            R.layout.item_search_result -> {
                val resultHolder = holder as SearchResultViewHolder
                val item = items[position] as ResultSearchRVItem.Result
                resultHolder.bind(item, onClick)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ResultSearchRVItem.AdResult -> R.layout.item_zero_search_ad
            is ResultSearchRVItem.Result -> R.layout.item_search_result
        }
    }
}