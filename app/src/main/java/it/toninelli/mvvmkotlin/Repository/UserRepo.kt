package it.toninelli.mvvmkotlin.Repository

import io.reactivex.Observable
import it.toninelli.mvvmkotlin.Di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.model.User
import it.toninelli.mvvmkotlin.Retrofit.ApiService

import javax.inject.Inject

@ApplicationScope
class UserRepo @Inject constructor(
        val apiService: ApiService

){
    fun getUserById(userId: Int): Observable<List<User>>{
        return apiService.getUser(userId)
                .filter { it.isNotEmpty() }
                .flatMap { Observable.just(it) }



    }

}