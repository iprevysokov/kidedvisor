package com.example.kidedvisor.club.domain.models

sealed class ClubState {
    data object Loading : ClubState()
    data class Success(val club: Club) : ClubState()
    data class Error(val message: String) : ClubState()
}