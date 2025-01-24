package com.example.kidedvisor.sample.domain.api

interface PopularRequestRepository {
    fun getPopularRequest(): List<String>
}