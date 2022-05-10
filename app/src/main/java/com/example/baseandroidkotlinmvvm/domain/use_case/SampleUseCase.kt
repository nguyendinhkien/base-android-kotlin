package com.example.baseandroidkotlinmvvm.domain.use_case

import com.example.baseandroidkotlinmvvm.domain.repository.ISampleRepository

import javax.inject.Inject


class SampleUseCase @Inject constructor(
    private val repository: ISampleRepository
) {
    suspend operator fun invoke() = repository.getSample()
}