package com.example.kidedvisor.search.domain.api

import com.example.kidedvisor.search.domain.models.AdBanner

interface GetAdBannerUseCase {
    fun execute(): AdBanner
}