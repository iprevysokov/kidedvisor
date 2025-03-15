package com.example.kidedvisor.club.domain.models

import java.util.UUID

data class Club(
    val id: UUID,
    val name: String,
    val address: String,
    val forAge: String,
    val contacts: String,
    val workDays: String,
    val workTime: String,
    val price: String,
    val schedule: String,
    val description: String,
    val photo: String,
    val isRecommend: Boolean = false,
)