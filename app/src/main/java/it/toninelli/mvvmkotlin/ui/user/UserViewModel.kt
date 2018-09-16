package it.toninelli.mvvmkotlin.ui.user

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import it.toninelli.mvvmkotlin.model.User
import it.toninelli.mvvmkotlin.Repository.UserRepo
import it.toninelli.mvvmkotlin.util.Resource
import javax.inject.Inject


class UserViewModel @Inject constructor(
        val repo: UserRepo
) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    val result = MutableLiveData<Resource<List<User>>>()




    fun loadUsers(id: Int){
        compositeDisposable.add(repo.loadUserById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { result.value = Resource.loading(null) }
                .subscribe({result.value = Resource.success(it)}, {result.value = Resource.error(it.localizedMessage,null)})

        )
    }


    override fun onCleared() {
        compositeDisposable.clear()
    }

}