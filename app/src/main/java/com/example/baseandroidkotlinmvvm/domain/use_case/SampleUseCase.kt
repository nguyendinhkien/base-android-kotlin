package com.example.baseandroidkotlinmvvm.domain.use_case

import com.example.baseandroidkotlinmvvm.domain.model.SimpleResponse
import com.example.baseandroidkotlinmvvm.domain.repository.ISampleRepository
import com.example.baseandroidkotlinmvvm.domain.use_case.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val repository: ISampleRepository
): SingleUseCase<SimpleResponse>() {
    override fun buildUseCaseSingle(): Single<SimpleResponse> {
        return repository.getSample()
    }



    fun saveParam(a: String){
        return
    }
}