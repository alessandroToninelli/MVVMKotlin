package it.toninelli.mvvmkotlin.Repository

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import it.toninelli.mvvmkotlin.Di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.model.User
import it.toninelli.mvvmkotlin.Retrofit.ApiService


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

