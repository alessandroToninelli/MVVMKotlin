package it.toninelli.mvvmkotlin.ViewModel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class UserViewModel @Inject constructor() : ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }


    fun prova(){
        println("prova dal vioew model")
    }
}