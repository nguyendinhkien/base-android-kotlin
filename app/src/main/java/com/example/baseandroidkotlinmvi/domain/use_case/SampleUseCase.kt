package com.example.baseandroidkotlinmvi.domain.use_case

import com.example.baseandroidkotlinmvi.domain.repository.ISampleRepository

import javax.inject.Inject


class SampleUseCase @Inject constructor(
    private val repository: ISampleRepository
) {
    suspend operator fun invoke() = repository.getSample()
}