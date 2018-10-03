package it.toninelli.mvvmkotlin.repository

import io.reactivex.Observable
import it.toninelli.mvvmkotlin.di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.model.User
import it.toninelli.mvvmkotlin.retrofit.ApiService
import it.toninelli.mvvmkotlin.util.BASE_URL
import it.toninelli.mvvmkotlin.util.BASE_URL_POST
import it.toninelli.mvvmkotlin.util.BASE_URL_USER


import javax.inject.Inject
import javax.inject.Named

@ApplicationScope
class UserRepo @Inject constructor(
       @Named("api_user") val apiService: ApiService

){

    fun loadUserById(id: Int): Observable<List<User>>{
        return apiService.getUser(id)
                        .filter { it.isNotEmpty() }
                        .flatMap { Observable.just(it) }

    }



}

