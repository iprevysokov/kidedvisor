package com.example.kidedvisor.search.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemInnerRvBinding

class InnerAdapter(private val clubs: List<String>) :
    RecyclerView.Adapter<InnerAdapter.ViewBinding>() {

    inner class ViewBinding(val binding: ItemInnerRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBinding {
        return ViewBinding(
            ItemInnerRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return clubs.size
    }

    override fun onBindViewHolder(holder: ViewBinding, position: Int) {
        val item = clubs[position]

        holder.binding.clubTitle.text = item
    }
}