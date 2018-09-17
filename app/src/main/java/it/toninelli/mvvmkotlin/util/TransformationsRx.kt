package it.toninelli.mvvmkotlin.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.MainThread
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TransformationsRx {

    companion object {

        @MainThread
        fun <X,  Y> switchMapRx(trigger: LiveData<X>, func: (X?) -> Observable<Y>):LiveData<Resource<Y>> {
            val result =  MediatorLiveData<Resource<Y>>()
            result.addSource(trigger){ id ->
                val newObs = func(id)
                lateinit var disposable: Disposable
                disposable = newObs
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { result.value = Resource.loading(null) }
                        .doOnComplete { disposable.dispose() }
                        .subscribe({
                            result.value = Resource.success(it)
                        },{ result.value = Resource.error(it.localizedMessage,null) })
            }
            return result
        }


    }



}