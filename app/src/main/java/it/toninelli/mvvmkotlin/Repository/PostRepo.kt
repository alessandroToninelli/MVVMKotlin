package it.toninelli.mvvmkotlin.Repository

import io.reactivex.Observable
import it.toninelli.mvvmkotlin.Di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.Model.Post
import it.toninelli.mvvmkotlin.Retrofit.ApiService

import javax.inject.Inject


@ApplicationScope
class PostRepo @Inject constructor( val apiService: ApiService){



    fun getPosts(): Observable<List<Post>> {
        return apiService.getPosts()
                .filter { it.isNotEmpty() }






    }


}