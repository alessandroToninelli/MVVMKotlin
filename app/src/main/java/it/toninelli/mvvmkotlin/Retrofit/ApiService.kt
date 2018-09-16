package it.toninelli.mvvmkotlin.Retrofit

import io.reactivex.Observable
import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.model.User
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService{


    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

    @GET("/posts")
    fun getPostById(@Query("id") id: Int): Observable<List<Post>>

    @GET("/users")
    fun getUser(@Query("id") id: Int): Observable<List<User>>
}