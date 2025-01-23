package com.example.kidedvisor.club.presentation.adapter.viewHolder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.databinding.ItemPhotoClubBinding

class PhotoClubViewHolder(private val view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPhotoClubBinding.bind(view)

    fun bind(photo: String) {
        // устанавливаем фотографии клуба
    }
}