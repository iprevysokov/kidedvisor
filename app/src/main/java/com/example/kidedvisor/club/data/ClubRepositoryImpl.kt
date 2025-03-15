package com.example.kidedvisor.club.data

import com.example.kidedvisor.club.domain.api.ClubRepository
import com.example.kidedvisor.club.domain.models.Club
import com.example.kidedvisor.club.domain.models.ClubConvertor
import com.example.kidedvisor.club.domain.models.Resource
import com.example.kidedvisor.core.roomdb.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class ClubRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val clubConvertor: ClubConvertor
) : ClubRepository {

    override suspend fun getClubById(id: UUID): Flow<Resource<Club>> = flow {
        val club = appDatabase.clubDao().getClubById(id)
        emit(Resource.Success(clubConvertor.mapToClub(club)))
    }
}