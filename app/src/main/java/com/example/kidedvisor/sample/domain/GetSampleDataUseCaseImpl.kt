package com.example.kidedvisor.sample.domain

import com.example.kidedvisor.sample.domain.api.GetSampleDataUseCase
import com.example.kidedvisor.sample.domain.api.SampleCollectionRepository

class GetSampleDataUseCaseImpl(
    private val sampleCollectionRepository: SampleCollectionRepository
) : GetSampleDataUseCase {

    override suspend fun execute() {
        sampleCollectionRepository.createSamplesData()
    }
}