package com.example.kidedvisor.sample.domain.api

import com.example.kidedvisor.search.domain.models.AdBanner

interface AdBannerRepository {
    fun getAdBanner(): AdBanner
}