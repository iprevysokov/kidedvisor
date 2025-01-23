package com.example.kidedvisor.search.ui.start_search

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidedvisor.databinding.ItemSearchStartClubBinding
import com.example.kidedvisor.search.domain.models.ClubInSearch

class StartSearchClubViewHolder(
    private val binding: ItemSearchStartClubBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(club: ClubInSearch) {

        Glide.with(binding.root)
            .load(club.image)
            .into(binding.clubImage)

        binding.apply {
            ratingBadge.text = "%f".format(club.rating)
            ratingBar.rating = club.rating.toFloat()
            clubTitle.text = club.name
            clubAddress.text = club.address
            typeIcon.setImageResource(club.branchIcon)
            clubType.text = "%s, %s".format(club.branch, club.type)
        }
    }
}