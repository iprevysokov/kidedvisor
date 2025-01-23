package com.example.kidedvisor.search.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemFilterTagBinding

class FilterTagAdapter(
) : RecyclerView.Adapter<FilterTagAdapter.ViewBinding>() {

    var filterTagList = emptyList<String>()

    inner class ViewBinding(
        val binding: ItemFilterTagBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterTagAdapter.ViewBinding {
        return ViewBinding(
            ItemFilterTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return filterTagList.size
    }

    override fun onBindViewHolder(holder: FilterTagAdapter.ViewBinding, position: Int) {
        val item = filterTagList[position]
        holder.binding.filterBtn.text = item
    }
}