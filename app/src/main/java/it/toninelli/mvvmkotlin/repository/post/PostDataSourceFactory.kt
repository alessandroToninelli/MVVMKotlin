package it.toninelli.mvvmkotlin.repository.post

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.retrofit.ApiService
import java.util.concurrent.Executor


class PostDataSourceFactory(
        private val apiService: ApiService,
        private val networkExecutor: Executor,
        private val compositeDisposable: CompositeDisposable
        ): DataSource.Factory<Int,Post>()
{

    val postDataSourceLiveData = MutableLiveData<ItemKeyedPostDataSource>()


    override fun create(): DataSource<Int, Post> {
        val postDataSource = ItemKeyedPostDataSource(apiService,networkExecutor, compositeDisposable)
        postDataSourceLiveData.postValue(postDataSource)
        return postDataSource
    }


}