package com.example.kidedvisor.search.ui.start_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kidedvisor.databinding.ItemSearchStartClubBinding
import com.example.kidedvisor.search.domain.models.ClubInSearch
import com.example.kidedvisor.search.presenter.models.SearchStartRVItem

class StartSearchClubViewHolder(
    private val binding: ItemSearchStartClubBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchStartRVItem.Club) {

        val club = item.clubInSearch

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

    companion object {
        fun newInstance(parent: ViewGroup): StartSearchClubViewHolder {
            return StartSearchClubViewHolder(
                ItemSearchStartClubBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}