package com.example.kidedvisor.club.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.R
import com.example.kidedvisor.club.presentation.adapter.clickListener.FeedbacksClickListener
import com.example.kidedvisor.club.presentation.adapter.viewHolder.FeedbackClubViewHolder

class FeedbacksAdapter(
    private val onItemClick: FeedbacksClickListener
) : RecyclerView.Adapter<FeedbackClubViewHolder>() {

    private val feedback = ArrayList<String>() // временная заглушка

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackClubViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_feedback, parent, false)
        return FeedbackClubViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: FeedbackClubViewHolder, position: Int) {
        holder.bind(feedback[position])

    }

    override fun getItemCount(): Int = feedback.size

}