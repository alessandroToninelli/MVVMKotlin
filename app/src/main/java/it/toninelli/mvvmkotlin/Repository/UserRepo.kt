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
        return apiService.getUser(userId).
                flatMap {
                    if(it.isEmpty())
                         Observable.just(Resource.error("Empty list", null))
                    else
                        Observable.just(Resource.success(it.get(0)))
                }
                .subscribeOn(Schedulers.newThread())

    }

}