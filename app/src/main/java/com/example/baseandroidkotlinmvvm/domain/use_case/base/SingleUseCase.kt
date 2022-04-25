package com.example.baseandroidkotlinmvvm.domain.use_case.base


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class SingleUseCase<T> : UseCase() {
    internal abstract suspend fun buildUseCaseSingle(): Single<T>
    suspend fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {}
    ) {
        disposeLast()
        lastDisposable = buildUseCaseSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onFinished)
//            .subscribe(onSuccess, onError)
            .subscribeWith(object : CallBackWrapper<T>(){
                override fun success(t: T) {
                    onSuccess.invoke(t)
                }

                override fun throwError(t: Throwable) {
                    onError.invoke(t)
                }
            })

        lastDisposable?.let {
            compositeDisposable.add(it)
        }

    }
}