package it.toninelli.mvvmkotlin.ui.user

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import it.toninelli.mvvmkotlin.repository.UserRepo
import it.toninelli.mvvmkotlin.util.TransformationsRx
import javax.inject.Inject


class UserViewModel @Inject constructor(
        val repo: UserRepo
) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    private val id = MutableLiveData<Int>()
    val result = TransformationsRx.switchMapRx(id){
        if(it!= null){
            repo.loadUserById(it)
        }else{
            Observable.empty()
        }
    }

    fun retry(){
        id.value?.let {
            id.value = it
        }
    }

    fun setId(userId: Int){
        id.value = userId
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

}