package it.toninelli.mvvmkotlin.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import it.toninelli.mvvmkotlin.Model.Post
import it.toninelli.mvvmkotlin.Model.User
import it.toninelli.mvvmkotlin.Repository.UserRepo
import it.toninelli.mvvmkotlin.Util.Resource
import javax.inject.Inject


class UserViewModel @Inject constructor(
        val repo: UserRepo
) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    val result = MutableLiveData<Resource<User>>()





    fun loadUser(id: Int){
        compositeDisposable.add(repo.getUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result.value = Resource.success(it.data)}, {result.value = Resource.error(it.localizedMessage,null)})



        )
    }


    override fun onCleared() {
        compositeDisposable.clear()
    }

}