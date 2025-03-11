package com.example.kidedvisor.search.domain.models

import androidx.annotation.DrawableRes
import com.example.kidedvisor.R
import java.util.UUID

data class ClubInSearch(
    val id: UUID,
    val name: String,
    val address: String,
    val branch: String,
    val type: String = "",
    @DrawableRes
    val branchIcon: Int = R.drawable.ic_sports_soccer_24,
    @DrawableRes
    val image: Int,
    val rating: Double
)