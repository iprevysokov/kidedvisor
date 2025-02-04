package com.example.kidedvisor.sample.data

import com.example.kidedvisor.R
import com.example.kidedvisor.sample.domain.api.AdBannerRepository
import com.example.kidedvisor.search.domain.models.AdBanner

class AdBannerRepositoryImpl : AdBannerRepository {
    override fun getAdBanner(): AdBanner {
        return AdBanner(R.drawable.banner_main)
    }
}