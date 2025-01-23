package com.example.kidedvisor.search.ui.start

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem

class StartAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<SearchStartRVItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is SearchStartRVItem.Header -> TODO()
            is SearchStartRVItem.Club -> TODO()
            is SearchStartRVItem.Request -> TODO()
        }
    }
}