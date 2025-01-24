package com.example.kidedvisor.sample.data

import com.example.kidedvisor.sample.domain.api.PopularRequestRepository

class PopularRequestRepositoryImpl : PopularRequestRepository {
    override fun getPopularRequest(): List<String> {
        return listOf(
            "Лепка из глины",
            "Теннис",
            "Рисование",
            "Декупаж",
            "Керамика",
        )
    }
}