package com.example.kidedvisor.search.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemOuterRvBinding
import com.example.kidedvisor.search.presenter.models.OuterModel

class OuterAdapter : RecyclerView.Adapter<OuterAdapter.ViewHolder>() {

    var itemList = emptyList<OuterModel>()

    inner class ViewHolder(val binding: ItemOuterRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOuterRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.binding.typeName.text = item.branchName
        holder.binding.innerRv.adapter = InnerAdapter(item.clubs)
    }
}