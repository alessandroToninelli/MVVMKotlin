package it.toninelli.mvvmkotlin.repository

import io.reactivex.Observable
import it.toninelli.mvvmkotlin.di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.model.User
import it.toninelli.mvvmkotlin.retrofit.ApiService


import javax.inject.Inject

@ApplicationScope
class UserRepo @Inject constructor(
        val apiService: ApiService

){

    fun loadUserById(id: Int): Observable<List<User>>{
            return apiService.getUser(id)
                        .filter { it.isNotEmpty() }
                        .flatMap { Observable.just(it) }
    }



}

