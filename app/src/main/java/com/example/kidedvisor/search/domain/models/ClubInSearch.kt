package com.example.kidedvisor.search.domain.models

import androidx.annotation.DrawableRes

data class ClubInSearch(
    val name: String,
    val address: String,
    val branch: String,
    val type: String,
    @DrawableRes
    val branchIcon: Int,
    @DrawableRes
    val image: Int,
    val rating: Int
)