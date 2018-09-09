package it.toninelli.mvvmkotlin.Repository

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import it.toninelli.mvvmkotlin.Di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.Model.User
import it.toninelli.mvvmkotlin.Retrofit.ApiService
import it.toninelli.mvvmkotlin.Util.AppExecutors
import it.toninelli.mvvmkotlin.Util.Resource

import javax.inject.Inject

@ApplicationScope
class UserRepo @Inject constructor(
        val apiService: ApiService

){
    fun getUserById(userId: Int): Observable<Resource<User>>{
        return apiService.getUser(userId)
                .filter { it.isNotEmpty() }
                .flatMap { Observable.just(Resource.success(it.get(0))) }


    }

}