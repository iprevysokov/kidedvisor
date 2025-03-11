package com.example.kidedvisor.search.ui.zero_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemZeroSearchSelectionBinding
import com.example.kidedvisor.search.presenter.models.ZeroSearchRVItem
import java.util.UUID

class ClubsSelectionViewHolder(
    private val binding: ItemZeroSearchSelectionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ZeroSearchRVItem.ClubSelectionItem, onClick: (UUID) -> Unit) {
        binding.typeName.text = item.clubsSelection.branchName
        binding.innerRv.adapter = InnerClubsSelectionAdapter(item.clubsSelection.clubs, onClick)
    }


    companion object {
        fun newInstance(parent: ViewGroup): ClubsSelectionViewHolder {
            return ClubsSelectionViewHolder(
                ItemZeroSearchSelectionBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}