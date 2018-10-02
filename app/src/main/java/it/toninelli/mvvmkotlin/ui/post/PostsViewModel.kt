package it.toninelli.mvvmkotlin.ui.post

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.repository.Listing
import it.toninelli.mvvmkotlin.repository.post.PostRepo
import it.toninelli.mvvmkotlin.util.Resource
import javax.inject.Inject

class PostsViewModel @Inject constructor(
                private val repo: PostRepo
): ViewModel() {

    private val compositeDisposable =  CompositeDisposable()

    val result = MutableLiveData<Resource<PagedList<Post>>>()

    var reporesult = repo.getPostsPaged(10,compositeDisposable)
    val networkState = reporesult.networkState
    val refreshState = reporesult.refreshState

    init {
        loadPosts()

    }

//    private fun loadPosts(){
//        compositeDisposable.addAll(
//                repo.getPosts()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnSubscribe { result.value = Resource.loading(null) }
//                        .subscribe({result.value = Resource.success(it)},
//                                {result.value = Resource.error(it.localizedMessage,null)})
//        )
//    }


    private fun loadPosts(){

        compositeDisposable.add(reporesult.pagedList
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { result.value = Resource.loading(null) }
                .subscribe({
                    result.value = Resource.success(it)
                },{
                    result.value = Resource.error(it.localizedMessage, null)
                })
        )

    }

//    fun retry(){
//        compositeDisposable.clear()
//        loadPosts()
//
//    }

    fun retry(){
        reporesult.retry.invoke()
    }

    fun refresh(){
        reporesult.refresh.invoke()
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}