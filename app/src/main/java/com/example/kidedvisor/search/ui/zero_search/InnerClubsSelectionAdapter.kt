package com.example.kidedvisor.search.ui.zero_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidedvisor.databinding.ItemInnerRvBinding
import com.example.kidedvisor.search.domain.models.ClubInSearch
import java.util.UUID

class InnerClubsSelectionAdapter(
    private val clubs: List<ClubInSearch>,
    private val onClick: (UUID) -> Unit
) :
    RecyclerView.Adapter<InnerClubsSelectionAdapter.ViewBinding>() {

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
        holder.binding.clubTitle.text = item.name

        Glide.with(holder.binding.root)
            .load(item.image)
            .into(holder.binding.image)

        holder.binding.ratingBadge.text = item.rating.toString()

        holder.binding.root.setOnClickListener { onClick(item.id) }
    }
}