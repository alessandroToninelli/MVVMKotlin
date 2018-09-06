package it.toninelli.mvvmkotlin.Retrofit

import io.reactivex.Observable
import it.toninelli.mvvmkotlin.Model.Post
import retrofit2.http.GET


interface ApiService{


    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}