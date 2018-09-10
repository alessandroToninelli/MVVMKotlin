package it.toninelli.mvvmkotlin.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import it.toninelli.mvvmkotlin.Model.Post
import it.toninelli.mvvmkotlin.Repository.PostRepo
import it.toninelli.mvvmkotlin.Util.Resource
import javax.inject.Inject

class PostsViewModel @Inject constructor(
                val repo: PostRepo
): ViewModel() {

    val compositeDisposable =  CompositeDisposable()
    val result = MutableLiveData<Resource<List<Post>>>()


    init {
        loadPosts()
    }

    fun loadPosts(){
        println("carico post")
        compositeDisposable.addAll(
                repo.getPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({result.value = Resource.success(it.data)}, {result.value = Resource.error(it.localizedMessage,null)})
        )
    }

    override fun onCleared() {
        println("canello")
        compositeDisposable.clear()
    }
}