package com.example.kidedvisor.search.domain.impl

import com.example.kidedvisor.sample.domain.api.AdBannerRepository
import com.example.kidedvisor.search.domain.api.GetAdBannerUseCase
import com.example.kidedvisor.search.domain.models.AdBanner

class GetAdBannerUseCaseImpl(
    private val repository: AdBannerRepository
) : GetAdBannerUseCase {

    override fun execute(): AdBanner {
        return repository.getAdBanner()
    }
}