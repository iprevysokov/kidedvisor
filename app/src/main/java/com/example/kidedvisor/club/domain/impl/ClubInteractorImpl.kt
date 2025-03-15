package com.example.kidedvisor.club.domain.impl

import com.example.kidedvisor.club.domain.api.ClubInteractor
import com.example.kidedvisor.club.domain.api.ClubRepository
import com.example.kidedvisor.club.domain.models.Club
import com.example.kidedvisor.club.domain.models.Resource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ClubInteractorImpl(private val clubRepository: ClubRepository) : ClubInteractor {
    override suspend fun getClubById(id: UUID): Flow<Resource<Club>> {
        return clubRepository.getClubById(id)
    }
}