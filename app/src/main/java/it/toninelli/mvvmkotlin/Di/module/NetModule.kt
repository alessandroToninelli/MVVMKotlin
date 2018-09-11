package it.toninelli.mvvmkotlin.Di.module

import dagger.Module
import dagger.Provides
import it.toninelli.mvvmkotlin.util.BASE_URL
import it.toninelli.mvvmkotlin.Di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.Retrofit.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetModule{


    @ApplicationScope
    @Provides
    fun provideApiService():ApiService{
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }



}