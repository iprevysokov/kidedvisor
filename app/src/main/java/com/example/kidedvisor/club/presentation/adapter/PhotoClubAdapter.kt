package com.example.kidedvisor.club.presentation.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.R
import com.example.kidedvisor.club.presentation.adapter.clickListener.PhotoClickListener
import com.example.kidedvisor.club.presentation.adapter.viewHolder.PhotoClubViewHolder

class PhotoClubAdapter(private val photoClickListener: PhotoClickListener) :
    RecyclerView.Adapter<PhotoClubViewHolder>() {

    private val photo = ArrayList<String>() // временная заглушка

    override fun onCreateViewHolder(
        parent: android.view.ViewGroup,
        viewType: Int
    ): PhotoClubViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo_club,
                 parent, false
            )
        return PhotoClubViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: PhotoClubViewHolder, position: Int) {
        holder.bind(photo[position])
        holder.itemView.setOnClickListener {
            photoClickListener.onPhotoClick(photo[position])
        }
    }

    override fun getItemCount(): Int = photo.size
}