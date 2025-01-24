package com.example.kidedvisor.sample.data

import com.example.kidedvisor.R
import com.example.kidedvisor.core.roomdb.entity.ClubEntity
import com.example.kidedvisor.sample.domain.api.PopularSearchRepository
import com.example.kidedvisor.search.domain.models.ClubInSearch
import java.util.UUID

class PopularSearchRepositoryImpl : PopularSearchRepository {
    override fun getPopularRequest(): List<String> {
        return listOf(
            "Лепка из глины",
            "Теннис",
            "Рисование",
            "Декупаж",
            "Керамика",
        )
    }

    override fun getPopularClubs(): List<ClubInSearch> {
        return listOf(
            ClubInSearch(
                rating = 4.2,
                name = "Школа искусств \"Тутти\"",
                address = "Металлистов, д.1",
                image = R.drawable.art1,
                branch = "Искусство"
            ),
            ClubInSearch(
                rating = 4.2,
                name = "Художественная студия \"Пикассо\"",
                address = "Металлистов, д.1",
                image = R.drawable.art2,
                branch = "Искусство",
            ),
            ClubInSearch(
                rating = 4.2,
                name = "Художественная студия \"Пикассо\"",
                address = "Металлистов, д.1",
                image = R.drawable.art3,
                branch = "Искусство",
            ),
            ClubInSearch(
                rating = 4.2,
                name = "Художественная студия \"Пикассо\"",
                address = "Металлистов, д.1",
                image = R.drawable.art4,
                branch = "Искусство"
            ),
            ClubInSearch(
                rating = 4.2,
                name = "Мир единоборств чертаново \"Пикассо\"",
                address = "Металлистов, д.1",
                image = R.drawable.sport2,
                branch = "Спорт"
            ),
        )
    }
}