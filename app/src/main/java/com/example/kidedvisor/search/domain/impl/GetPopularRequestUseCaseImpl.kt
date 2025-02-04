package com.example.kidedvisor.search.domain.impl

import com.example.kidedvisor.sample.domain.api.PopularSearchRepository
import com.example.kidedvisor.search.domain.api.GetPopularRequestUseCase

class GetPopularRequestUseCaseImpl(
    private val repository: PopularSearchRepository
) : GetPopularRequestUseCase {

    override fun execute(): List<String> {
        return repository.getPopularRequest()
    }
}