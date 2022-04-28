package com.example.baseandroidkotlinmvvm.domain.use_case.base


import com.example.baseandroidkotlinmvvm.core.ErrorNetworkHandler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class SingleUseCase<T : Any> : UseCase() {
    internal abstract fun buildUseCaseSingle(): Single<T>
    fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {}
    ) {
        disposeLast()
        lastDisposable = buildUseCaseSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onFinished)
//            .compose(ErrorNetworkHandler())
//            .subscribe(onSuccess, onError)
            .subscribeWith(object : CallBackWrapper<T>() {
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