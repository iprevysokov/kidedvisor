package com.example.kidedvisor.sample.data

import com.example.kidedvisor.R
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
                id = UUID.fromString("8C0A9050-F44A-4766-8E48-FAEA54369708"),
                rating = 4.2,
                name = "Школа искусств \"Тутти\"",
                address = "Металлистов, д.1",
                image = R.drawable.art1,
                branch = "Искусство",
                type = "Танцы",
            ),
            ClubInSearch(
                id = UUID.fromString("E18BA298-B80D-46BE-9D72-CEA20176DA59"),
                rating = 4.2,
                name = "Художественная студия \"Пикассо\"",
                address = "Металлистов, д.1",
                image = R.drawable.art2,
                branch = "Искусство",
                type = "Рисование",
            ),
            ClubInSearch(
                id = UUID.fromString("73245E2F-0152-427A-AD1A-1B034595EC52"),
                rating = 4.2,
                name = "Художественная студия \"Пикассо\"",
                address = "Металлистов, д.1",
                image = R.drawable.art3,
                branch = "Искусство",
                type = "Рисование",
            ),
            ClubInSearch(
                id = UUID.fromString("D29D68E2-27A6-4888-9247-784E68BF1E13"),
                rating = 4.2,
                name = "Художественная студия \"Пикассо\"",
                address = "Металлистов, д.1",
                image = R.drawable.art4,
                branch = "Искусство",
                type = "Рисование",
            ),
            ClubInSearch(
                id = UUID.fromString("8A7565BD-F1C6-4830-90FD-9C3F0360CE4E"),
                rating = 4.2,
                name = "Мир единоборств чертаново \"Пикассо\"",
                address = "Металлистов, д.1",
                image = R.drawable.sport2,
                branch = "Спорт",
                type = "Борьба"
            ),
        )
    }
}