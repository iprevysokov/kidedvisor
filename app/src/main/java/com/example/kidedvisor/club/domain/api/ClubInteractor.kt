package com.example.kidedvisor.club.domain.api

import com.example.kidedvisor.club.domain.models.Club
import com.example.kidedvisor.club.domain.models.Resource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ClubInteractor {
   suspend fun getClubById(id: UUID): Flow<Resource<Club>>
}