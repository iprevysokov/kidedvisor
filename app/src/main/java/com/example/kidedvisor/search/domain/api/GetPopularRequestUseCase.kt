package com.example.kidedvisor.search.domain.api

interface GetPopularRequestUseCase {
    fun execute(): List<String>
}