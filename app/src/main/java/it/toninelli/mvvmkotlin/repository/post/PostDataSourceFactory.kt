package it.toninelli.mvvmkotlin.repository.post

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.model.RedditPost
import it.toninelli.mvvmkotlin.retrofit.ApiService
import java.util.concurrent.Executor


class PostDataSourceFactory(
        private val apiService: ApiService,
        private val networkExecutor: Executor,
        private val compositeDisposable: CompositeDisposable
        ): DataSource.Factory<String,RedditPost>()
{

    val postDataSourceLiveData = MutableLiveData<PageKeyedPostDataSource>()


    override fun create(): DataSource<String, RedditPost> {
        val postDataSource = PageKeyedPostDataSource(apiService,networkExecutor, compositeDisposable)
        postDataSourceLiveData.postValue(postDataSource)
        return postDataSource
    }


}