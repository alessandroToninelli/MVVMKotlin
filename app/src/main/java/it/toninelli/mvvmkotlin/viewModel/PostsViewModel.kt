package it.toninelli.mvvmkotlin.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.Repository.PostRepo
import it.toninelli.mvvmkotlin.util.Resource
import javax.inject.Inject

class PostsViewModel @Inject constructor(
                private val repo: PostRepo
): ViewModel() {

    private val compositeDisposable =  CompositeDisposable()
    val result = MutableLiveData<Resource<List<Post>>>()


    init {
        loadPosts()
    }

    private fun loadPosts(){
        compositeDisposable.addAll(
                repo.getPosts()
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