package it.toninelli.mvvmkotlin.repository.post

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.repository.NetworkState
import it.toninelli.mvvmkotlin.retrofit.ApiService
import java.util.concurrent.Executor
import it.toninelli.mvvmkotlin.repository.NetworkState.Companion.LOADED
import it.toninelli.mvvmkotlin.repository.NetworkState.Companion.LOADING


class ItemKeyedPostDataSource(
        private val apiService: ApiService,
        private val executor: Executor,
        private val compositeDisposable: CompositeDisposable
        ) : ItemKeyedDataSource<Int, Post>(){



    private var retry : (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed(){
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            executor.execute {
                it.invoke()
            }
        }
    }


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Post>) {



        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        compositeDisposable.add(apiService.getPosts()
                .flatMap { Observable.fromArray(it.body() ?: emptyList()) }
                .subscribe({
                    retry = null
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)
                    callback.onResult(it)
                },{
                    retry = {
                        loadInitial(params,callback)
                    }
                    val error = NetworkState.error(it.message)
                    networkState.postValue(error)
                    initialLoad.postValue(error)
                })
        )

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Post>) {



        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(apiService.getPosts()
                .flatMap { Observable.fromArray(it.body() ?: emptyList()) }
                .subscribe({
                    retry = null
                    networkState.postValue(NetworkState.LOADED)
                    callback.onResult(it)
                },{
                    retry = {
                        loadAfter(params,callback)
                    }
                    val error = NetworkState.error(it.message)
                    networkState.postValue(error)
                })
        )


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Post>) {

    //ignore

    }


    override fun getKey(item: Post) = item.id




}