package it.toninelli.mvvmkotlin.repository.post

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import it.toninelli.mvvmkotlin.di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.model.RedditPost
import it.toninelli.mvvmkotlin.repository.Listing
import it.toninelli.mvvmkotlin.retrofit.ApiService
import it.toninelli.mvvmkotlin.util.AppExecutors
import it.toninelli.mvvmkotlin.repository.post.PostDataSourceFactory

import javax.inject.Inject
import javax.inject.Named


@ApplicationScope
class PostRepo @Inject constructor(
        @Named("api_post") private  val apiService: ApiService,
        private val appExecutors: AppExecutors){




    fun getPosts(): Observable<List<Post>> {
        return apiService.getPosts()
                .flatMap { Observable.fromArray(it.body()) }

    }


    fun getPostById(id: Int):Observable<List<Post>>{
        return apiService.getPostById(id)
                .filter{ it.isNotEmpty()
                }
    }



    fun getPostsPaged(pageSize: Int):Listing<RedditPost>{
        val sourceFactory = PostDataSourceFactory(apiService, appExecutors.networkIO())
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize*2)
                .setEnablePlaceholders(false)
                .build()
        val pagedList = RxPagedListBuilder(sourceFactory,config).buildObservable()
        val refreshState = Transformations.switchMap(sourceFactory.postDataSourceLiveData){
            it.initialLoad
        }


        val networkState = Transformations.switchMap(sourceFactory.postDataSourceLiveData){
            it.networkState
        }



        return Listing(

                dataSource = sourceFactory.postDataSourceLiveData,

                pagedList = pagedList,
                networkState = networkState,
                retry = {
                    sourceFactory.postDataSourceLiveData.value?.retryAllFailed()
                },
                refresh = {
                    sourceFactory.postDataSourceLiveData.value?.invalidate()
                },
                refreshState = refreshState,

                clear = {
                    sourceFactory.postDataSourceLiveData.value?.clear()
                }

        )

    }












}