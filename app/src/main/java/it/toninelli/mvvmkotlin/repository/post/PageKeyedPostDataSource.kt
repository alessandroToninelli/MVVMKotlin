package it.toninelli.mvvmkotlin.repository.post

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import android.arch.paging.PageKeyedDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.model.RedditPost
import it.toninelli.mvvmkotlin.repository.NetworkState
import it.toninelli.mvvmkotlin.retrofit.ApiService
import java.util.concurrent.Executor
import it.toninelli.mvvmkotlin.repository.NetworkState.Companion.LOADED
import it.toninelli.mvvmkotlin.repository.NetworkState.Companion.LOADING


class PageKeyedPostDataSource(
        private val apiService: ApiService,
        private val executor: Executor) : PageKeyedDataSource<String, RedditPost>(){



    val compositeDisposable = CompositeDisposable()
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


    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String,RedditPost>) {



        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        compositeDisposable.add(apiService.getTop("androiddev",params.requestedLoadSize)
                .map { response -> response.body()?.data}
                .subscribe({
                    retry = null
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)
                    callback.onResult(it?.children?.map { it.data }?: emptyList(),it?.before, it?.after)
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

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String,RedditPost>) {


        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(apiService.getTopAfter("androiddev", params.key,params.requestedLoadSize)
                .map { response -> response.body()?.data}
                .subscribe({
                    retry = null
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)
                    callback.onResult(it?.children?.map { it.data }?: emptyList(),it?.after)
                },{
                    retry = {
                        loadAfter(params,callback)
                    }
                    val error = NetworkState.error(it.message)
                    networkState.postValue(error)
                })
        )


    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String,RedditPost>) {

    //ignore

    }

    fun clear (){

        compositeDisposable.clear()
    }








}