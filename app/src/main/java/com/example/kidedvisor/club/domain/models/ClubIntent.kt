package com.example.kidedvisor.club.domain.models

import java.util.UUID

sealed class ClubIntent {
    data class LoadClub(val id: UUID) : ClubIntent()
}