package com.example.kidedvisor.search.ui.result_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidedvisor.databinding.ItemSearchResultBinding
import com.example.kidedvisor.search.domain.models.ClubInSearch

class SearchResultViewHolder(
    private val binding: ItemSearchResultBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(club: ClubInSearch) {
        binding.apply {

            Glide.with(binding.root)
                .load(club.image)
                .into(image)

            ratingBadge.text = club.rating.toString()
            ratingBar.rating = club.rating.toFloat()
            clubTitle.text = club.name
            clubAddress.text = club.address

            Glide.with(binding.root)
                .load(club.branchIcon)
                .into(typeIcon)

            clubType.text = "${club.branch}, ${club.type}"

        }
    }

    companion object {
        fun newInstance(parent: ViewGroup): SearchResultViewHolder {
            return SearchResultViewHolder(
                ItemSearchResultBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}