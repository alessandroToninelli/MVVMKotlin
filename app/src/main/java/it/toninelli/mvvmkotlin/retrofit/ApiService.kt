package it.toninelli.mvvmkotlin.retrofit

import io.reactivex.Observable
import it.toninelli.mvvmkotlin.model.ListingResponse
import it.toninelli.mvvmkotlin.model.Post
import it.toninelli.mvvmkotlin.model.RedditPost
import it.toninelli.mvvmkotlin.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService{


    @GET("/posts")
    fun getPosts(): Observable<Response<List<Post>>>

    @GET("/posts")
    fun getPostById(@Query("id") id: Int): Observable<List<Post>>

    @GET("/users")
    fun getUser(@Query("id") id: Int): Observable<List<User>>



    @GET("/r/{subreddit}/hot.json")
    fun getTop(
            @Path("subreddit") subreddit: String,
            @Query("limit") limit: Int): Observable<Response<ListingResponse>>


    @GET("/r/{subreddit}/hot.json")
    fun getTopAfter(
            @Path("subreddit") subreddit: String,
            @Query("after") after: String,
            @Query("limit") limit: Int): Observable<Response<ListingResponse>>





}