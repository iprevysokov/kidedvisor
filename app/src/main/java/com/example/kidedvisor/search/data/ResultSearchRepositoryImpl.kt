package com.example.kidedvisor.search.data

import com.example.kidedvisor.R
import com.example.kidedvisor.core.roomdb.AppDatabase
import com.example.kidedvisor.core.roomdb.entity.ClubEntity
import com.example.kidedvisor.search.data.converters.ClubDbConverter
import com.example.kidedvisor.search.domain.api.ResultSearchRepository
import com.example.kidedvisor.search.domain.models.ClubInSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ResultSearchRepositoryImpl(
    private val db: AppDatabase,
    private val converter: ClubDbConverter
) : ResultSearchRepository {

    override suspend fun getAdClub(): ClubInSearch {
        return ClubInSearch(
            rating = 4.2,
            name = "Мир единоборств чертаново \"Пикассо\"",
            address = "Металлистов, д.1",
            image = R.drawable.sport2,
            branch = "Спорт",
            type = "Борьба"
        )
    }

    override suspend fun getSearchResult(): Flow<List<ClubInSearch>> = flow {
        emit(
            convertFromDb(db.clubDao().getAllClubs())
        )
    }

    private fun convertFromDb(entities: List<ClubEntity>): List<ClubInSearch> {
        return entities.map { converter.map(it)}
    }
}