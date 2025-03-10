package com.example.kidedvisor.club.presentation.adapter.viewHolder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.club.presentation.adapter.PhotoClubAdapter
import com.example.kidedvisor.club.presentation.adapter.clickListener.FeedbacksClickListener
import com.example.kidedvisor.databinding.ItemFeedbackBinding

class FeedbackClubViewHolder(
    private val view: View,
    private val onItemClickListener: FeedbacksClickListener
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemFeedbackBinding.bind(view)

    fun bind(feedback: String) {
        // Прописываем полученную информацию

        val photoAdapter = PhotoClubAdapter {

        }
        binding.recyclerView.adapter = photoAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

        binding.comment.setOnClickListener {
            onItemClickListener.onCommentClick(toString())
        }

        binding.additionallyFeedback.setOnClickListener {
            onItemClickListener.onMoreClick(toString())
        }

        view.setOnClickListener {
            onItemClickListener.onFeedbacksClick(toString())
        }

    }

}