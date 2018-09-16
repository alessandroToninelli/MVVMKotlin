package it.toninelli.mvvmkotlin.Repository

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.toninelli.mvvmkotlin.Di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.Retrofit.ApiService
import it.toninelli.mvvmkotlin.model.User

import javax.inject.Inject


@ApplicationScope
class PostRepo @Inject constructor( val apiService: ApiService){

    lateinit var emitter : ObservableEmitter<List<Post>>


    lateinit var disposable: Disposable

    fun getPosts(): Observable<List<Post>> {
        return apiService.getPosts()
                .filter { it.isNotEmpty() }

    }


    fun loadPostById(emitter: ObservableEmitter<List<Post>>){
        this.emitter = emitter
    }

    fun  setId(id: Int){
        disposable = apiService.getPostById(id)
                .subscribeOn(Schedulers.io())
                .doOnComplete { disposable.dispose() }
                .doOnNext {emitter.onNext(it)}
                .filter { it.isNotEmpty() }
                .subscribe()

    }






    fun clear() = disposable.dispose()






}